package com.igorj.limboapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val BrightOrange = Color(0xFFEC5500)
val DarkOrange = Color(0xFFF90800)
val LightOrange =  Color(0xFFFDBB00)
val DarkBackground = Color(0xFF0D0D0D)
val LightGray = Color(0xFF808080)
val DarkGray = Color(0xFF181818)
val TextWhite = Color(0xFFFFFFFF)
val LightGreen = Color(0xFF00F90A)
val Green = Color(0xFF3FB105)
val SemiDarkGreen = Color(0xFF1F7600)
val DarkGreen = Color(0xFF12350A)
val BrightRed = Color(0xFFF90800)
val SemiDarkRed = Color(0xFF580A05)
val DarkRed = Color(0xFF1A0A09)
val CircleGray = Color(0xFF1E1E1E)

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

val GreenGradient = Brush.horizontalGradient(
    colors = listOf(
        Green,
        DarkGreen
    )
)

val LightGreenGradient = Brush.horizontalGradient(
    colors = listOf(
        LightGreen,
        SemiDarkGreen
    )
)

val RedGradient = Brush.horizontalGradient(
    colors = listOf(
        SemiDarkRed,
        DarkRed,
    )
)

val BrightOrangeGradient = Brush.horizontalGradient(
    colors = listOf(
        LightOrange,
        BrightRed,
    )
)

val DarkBlackGradient = Brush.horizontalGradient(
    colors = listOf(
        DarkBackground,
        Color(0xFF121212),
        Color(0xFF151515),
        Color(0xFF141414),
        Color(0xFF111111)
    )
)

val DarkVerticalQuizBackgroundGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF47200D),
        Color(0xFF2D1B0D),
        Color(0xFF1D160D),
        Color(0xFF14120D),
        Color(0xFF11100E),
        Color(0xFF0F0E0E),
        Color(0xFF0F0E0E),
        Color(0xFF0F0E0E)
    )
)