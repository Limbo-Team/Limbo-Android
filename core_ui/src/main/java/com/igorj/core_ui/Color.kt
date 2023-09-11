package com.igorj.core_ui

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val BrightOrange = Color(0xFFEC5500)
val DarkOrange = Color(0xFFF90800)
val LightOrange =  Color(0xFFFDBB00)
val DarkBackground = Color(0xFF0D0D0D)
val LightGray = Color(0xFF808080)
val DarkGray = Color(0xFF181818)
val TextWhite = Color(0xFFFFFFFF)

val OrangeGradient = Brush.horizontalGradient(
    colors = listOf(
        DarkOrange,
        BrightOrange,
        LightOrange
    )
)

val BlackGradient = Brush.horizontalGradient(
    colors = listOf(
        DarkBackground,
        DarkGray,
        DarkBackground
    )
)