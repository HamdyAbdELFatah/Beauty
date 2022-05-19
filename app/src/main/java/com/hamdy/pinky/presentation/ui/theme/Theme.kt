package com.hamdy.pinky.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.hamdy.pinky.presentation.theme.Shapes
import com.hamdy.pinky.presentation.theme.Typography

private val DarkColorPalette = darkColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary
)

private val LightColorPalette = lightColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    //background = background,
   // surface = surface,
//    onPrimary = Color.Blue,
//    onSecondary = Color.Black,
//    onBackground = primary,
//    onSurface = primary,

)

@Composable
fun PinkyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}