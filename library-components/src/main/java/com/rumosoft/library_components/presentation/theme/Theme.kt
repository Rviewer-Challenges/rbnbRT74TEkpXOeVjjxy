package com.rumosoft.library_components.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Black,
    primaryVariant = DarkGray,
    secondary = Blue,
    secondaryVariant = CadetBlue,
    surface = Black,
    onPrimary = White,
    onSecondary = Black,
    onSurface = White,
    onBackground = Mystic,
)

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = DarkGray,
    secondary = Blue,
    secondaryVariant = Gallery,
    surface = White,
    onPrimary = Black,
    onSecondary = Black,
    onSurface = Black,
    onBackground = ShuttleGray,
)

@Composable
fun TwitterMirroringTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
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

object TwitterMirroringTheme {
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors

    val extraColors: CustomColors
        @Composable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes

    val paddings: Paddings
        @Composable
        get() = LocalPaddings.current
}

object ClearRippleTheme : RippleTheme { @Composable override fun defaultColor(): Color = Color.Transparent @Composable override fun rippleAlpha() = RippleAlpha( draggedAlpha = 0.0f, focusedAlpha = 0.0f, hoveredAlpha = 0.0f, pressedAlpha = 0.0f, ) }
