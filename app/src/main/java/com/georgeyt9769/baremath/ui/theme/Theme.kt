package com.georgeyt9769.baremath.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Svetlá paleta farieb inšpirovaná obrázkom
private val LightColorScheme = lightColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF6750A4),
    onPrimary = androidx.compose.ui.graphics.Color.White,
    primaryContainer = androidx.compose.ui.graphics.Color(0xFFEADDFF), // Farba tlačidla AC
    onPrimaryContainer = androidx.compose.ui.graphics.Color(0xFF21005D),
    secondary = androidx.compose.ui.graphics.Color(0xFFE8DEF8), // Farba pre operátory a =
    onSecondary = androidx.compose.ui.graphics.Color(0xFF1D192B),
    background = androidx.compose.ui.graphics.Color(0xFFF6F1F8), // Farba pozadia
    onBackground = androidx.compose.ui.graphics.Color(0xFF1D1B20), // Farba textu na displeji
    surface = androidx.compose.ui.graphics.Color(0xFFF6F1F8),
    onSurface = androidx.compose.ui.graphics.Color(0xFF1D1B20),
    surfaceVariant = androidx.compose.ui.graphics.Color(0xFFE7E0EC), // Farba číselných tlačidiel
    onSurfaceVariant = androidx.compose.ui.graphics.Color(0xFF49454F)
)

@Composable
fun MinimalCalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // V tejto kalkulačke budeme mať len jednu, svetlú tému pre jednoduchosť
    val colorScheme = LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Toto sa teraz správne načíta z Type.kt
        content = content
    )
}