package com.rumosoft.library_components.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rumosoft.library_components.R

val helveticaNeue = FontFamily(
    Font(R.font.helvetica_neue_regular),
    Font(R.font.helvetica_neue_bold, weight = FontWeight.Bold)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp,
    ),
    h2 = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
    ),
    h3 = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
    ),
    h4 = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
    ),
    h5 = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 15.sp,
    ),
    h6 = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
    ),
    body1 = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
    ),
    body2 = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
    ),
    button = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
    ),
    caption = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
    ),
    overline = TextStyle(
        fontFamily = helveticaNeue,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
    ),
)
