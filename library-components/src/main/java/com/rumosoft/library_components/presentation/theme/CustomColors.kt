package com.rumosoft.library_components.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColors(
    val blue: Color = Blue,
    val black: Color = Black,
    val white: Color = White,
    val darkGray: Color = DarkGray,
    val lightGray: Color = LightGray,
    val extraLightGray: Color = ExtraLightGray,
    val extraExtraLightGray: Color = ExtraExtraLightGray,
)

internal val LocalColors = staticCompositionLocalOf { CustomColors() }
