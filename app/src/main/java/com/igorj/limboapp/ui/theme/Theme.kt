package com.igorj.limboapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.igorj.core_ui.BrightOrange
import com.igorj.core_ui.DarkBackground
import com.igorj.core_ui.DarkOrange
import com.igorj.core_ui.Dimensions
import com.igorj.core_ui.LightGray
import com.igorj.core_ui.LightOrange
import com.igorj.core_ui.LocalSpacing
import com.igorj.core_ui.TextWhite

private val DarkColorPalette = darkColors(
    primary = BrightOrange,
    primaryVariant = DarkOrange,
    secondary = LightOrange,
    background = DarkBackground,
    onBackground = TextWhite,
    surface = LightGray,
    onSurface = TextWhite,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

private val LightColorPalette = lightColors(
    primary = BrightOrange,
    primaryVariant = DarkOrange,
    secondary = LightOrange,
    background = DarkBackground,
    onBackground = TextWhite,
    surface = LightGray,
    onSurface = TextWhite,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

@Composable
fun LimboAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    CompositionLocalProvider(LocalSpacing provides Dimensions()) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}