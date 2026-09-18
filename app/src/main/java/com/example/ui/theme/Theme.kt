package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = EngagePrimaryDark,
    onPrimary = Color.White,
    primaryContainer = EngagePrimaryContainerDark,
    onPrimaryContainer = Color(0xFFD6D7FF),
    secondary = EngagePrimary,
    onSecondary = Color.White,
    background = EngageBackgroundDark,
    onBackground = EngageTextPrimaryDark,
    surface = EngageSurfaceDark,
    onSurface = EngageTextPrimaryDark,
    surfaceVariant = EngageSurfaceVariantDark,
    onSurfaceVariant = EngageTextSecondaryDark,
    outline = EngageCardBorderDark,
    error = EngageError,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = EngagePrimary,
    onPrimary = Color.White,
    primaryContainer = EngagePrimaryContainer,
    onPrimaryContainer = EngagePrimary,
    secondary = EngagePrimaryDark,
    onSecondary = Color.White,
    background = EngageBackgroundLight,
    onBackground = EngageTextPrimaryLight,
    surface = EngageSurfaceLight,
    onSurface = EngageTextPrimaryLight,
    surfaceVariant = EngageSurfaceVariantLight,
    onSurfaceVariant = EngageTextSecondaryLight,
    outline = EngageCardBorderLight,
    error = EngageError,
    onError = Color.White
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

