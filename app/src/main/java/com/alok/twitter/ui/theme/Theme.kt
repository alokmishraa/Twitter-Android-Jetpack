package com.alok.twitter.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = TwitterBlue,
    primaryVariant = Color.Black,
    secondary = TwitterGray,
    background = Color.Black,
    surface = Color.Black,
    onPrimary = Color.White,
    onSecondary = TwitterGray,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Red800,
)

private val LightColorPalette = lightColors(
    primary = TwitterBlue,
    primaryVariant = Color.White,
    secondary = TwitterGray,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.Black,
    onSecondary = TwitterGray,
    onBackground = Color.Black,
    onSurface = Color.White,
    error = Red800,
)

@Composable
fun TwitterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = com.alok.twitter.ui.theme.Typography,
        shapes = Shapes,
        content = content
    )
}

object MyCustomTheme {
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors

    val typography: androidx.compose.material.Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes

    val paddings: Padding
        @Composable
        get() = LocalPaddings.current
}

@Composable
fun SplashTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        shapes = Shapes,
        content = content
    )
}