package com.example.a221003_ng_nelson_lab4.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 🔴 Replace these with YOUR generated colors if different
val primaryLight = Color(0xFFE53935)
val onPrimaryLight = Color.White
val secondaryLight = Color(0xFF775653)
val onSecondaryLight = Color.White
val backgroundLight = Color(0xFFFFFBFF)
val onBackgroundLight = Color(0xFF201A19)
val surfaceLight = Color.White
val onSurfaceLight = Color.Black

val primaryDark = Color(0xFFFFB4AB)
val onPrimaryDark = Color.Black
val secondaryDark = Color(0xFFE7BDB8)
val onSecondaryDark = Color.Black
val backgroundDark = Color(0xFF201A19)
val onBackgroundDark = Color.White
val surfaceDark = Color(0xFF201A19)
val onSurfaceDark = Color.White

// ✅ Light Theme
val LightColors = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight
)

// ✅ Dark Theme
val DarkColors = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark
)

// ✅ Typography
val AppTypography = Typography()

// ✅ Theme Function
@Composable
fun A221003_Ng_Nelson_Lab4Theme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}