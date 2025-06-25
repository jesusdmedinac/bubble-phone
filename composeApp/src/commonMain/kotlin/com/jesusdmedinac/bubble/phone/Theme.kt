package com.jesusdmedinac.bubble.phone

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Monochromatic color palette with different shades
private val Black = Color.Black
private val DarkGray = Color(0xFF1E1E1E)
private val MediumGray = Color(0xFF424242)
private val LightGray = Color(0xFF757575)
private val OffWhite = Color(0xFFEEEEEE)
private val White = Color.White
private val ErrorRed = Color(0xFFCF6679)

private val colorScheme = darkColorScheme(
    // Primary colors
    primary = White,
    onPrimary = Black,
    primaryContainer = LightGray,
    onPrimaryContainer = White,
    inversePrimary = Black,
    
    // Secondary colors (slightly different from primary for visual hierarchy)
    secondary = OffWhite,
    onSecondary = Black,
    secondaryContainer = MediumGray,
    onSecondaryContainer = White,
    
    // Tertiary colors (for accents)
    tertiary = LightGray,
    onTertiary = White,
    tertiaryContainer = MediumGray,
    onTertiaryContainer = White,
    
    // Background and surface colors
    background = Black,
    onBackground = OffWhite,
    surface = DarkGray,
    onSurface = White,
    surfaceVariant = MediumGray,
    onSurfaceVariant = OffWhite,
    surfaceTint = White,
    
    // Inverse colors
    inverseSurface = OffWhite,
    inverseOnSurface = DarkGray,
    
    // Error colors
    error = ErrorRed,
    onError = White,
    errorContainer = Color(0x1FCF6679),
    onErrorContainer = ErrorRed,
    
    // Outline
    outline = LightGray,
    outlineVariant = MediumGray
)

@Composable
fun BubblePhoneTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}
