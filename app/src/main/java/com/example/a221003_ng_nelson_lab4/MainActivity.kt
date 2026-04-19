package com.example.a221003_ng_nelson_lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.a221003_ng_nelson_lab4.ui.theme.A221003_Ng_Nelson_Lab4Theme

data class UserData(
    val username: String = "",
    val incidentType: String = ""
)

class MainViewModel : ViewModel() {

    var userData by mutableStateOf(UserData())
        private set

    fun setUsername(name: String) {
        userData = userData.copy(username = name)
    }

    fun setIncident(type: String) {
        userData = userData.copy(incidentType = type)
    }
}

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Report : Screen("report")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            A221003_Ng_Nelson_Lab4Theme {

                val navController = rememberNavController()
                val viewModel: MainViewModel = viewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Login.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable(Screen.Login.route) {
                            LoginScreen(
                                onLoginClick = { username ->
                                    viewModel.setUsername(username)
                                    navController.navigate(Screen.Home.route)
                                }
                            )
                        }

                        composable(Screen.Home.route) {
                            HomeScreen(
                                username = viewModel.userData.username,
                                onReportClick = {
                                    navController.navigate(Screen.Report.route)
                                }
                            )
                        }

                        composable(Screen.Report.route) {
                            ReportScreen(viewModel)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun LoginScreen(onLoginClick: (String) -> Unit) {

        var username by remember { mutableStateOf("") }

        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Community Safety Login", fontSize = 24.sp, color = Color.White)

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Enter Your Name") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = {
                    onLoginClick(username)
                }) {
                    Text("Login")
                }
            }
        }
    }

    @Composable
    fun HomeScreen(
        username: String,
        onReportClick: () -> Unit
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Welcome, $username", fontSize = 20.sp, color = Color.White)

                Button(onClick = onReportClick) {
                    Text("Report Incident")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FeatureBox("Police Station", R.drawable.police)
                    FeatureBox("Hospital", R.drawable.hospital)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FeatureBox("Fire Station", R.drawable.fire)
                    FeatureBox("Rela", R.drawable.rela)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FeatureBox("Embassy", R.drawable.embassy)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }

    @Composable
    fun ReportScreen(viewModel: MainViewModel) {

        val incidentTypes = listOf("Robbery", "Fire", "Accident")

        var selected by remember { mutableStateOf(incidentTypes[0]) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Select Incident", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(20.dp))

            incidentTypes.forEach {
                Button(
                    onClick = {
                        selected = it
                        viewModel.setIncident(it)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(it)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Selected: ${viewModel.userData.incidentType}")
        }
    }

    @Composable
    fun FeatureBox(title: String, imageRes: Int) {

        var expanded by remember { mutableStateOf(false) }

        Card(
            modifier = Modifier.size(if (expanded) 150.dp else 130.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            onClick = { expanded = !expanded }
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier.size(50.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(title)

                if (expanded) {
                    Text("Tap for help", fontSize = 12.sp)
                }
            }
        }
    }
}