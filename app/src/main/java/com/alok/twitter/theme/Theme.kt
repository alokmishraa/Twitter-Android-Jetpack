package com.alok.twitter.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
	// Main Colors
	primary = Color(0xFFB33737),
	primaryVariant = Color(0xFF510000),

	secondary = Color(0xFFFF8282),
	secondaryVariant = Color(0xFFCB4D4D),

	// Surfaces Colors
	background = Color(0xFF181818),
	surface = Color(0xFF181818),
	error = Color(0xFFFF0000),

	onPrimary = Color.White,
	onSecondary = Color.Black,
	onBackground = Color.White,
	onSurface = Color.White,
	onError = Color.White,
)

private val LightColorPalette = lightColors(
	// Main Colors
	primary = Color(0xFFB33737),
	primaryVariant = Color(0xFF510000),

	secondary = Color(0xFFFF8282),
	secondaryVariant = Color(0xFFCB4D4D),

	// Surfaces Colors
	background = Color(0xFFfafafa),
	surface = Color(0xFFfafafa),
	error = Color(0xFFFF0000),

	onPrimary = Color.White,
	onSecondary = Color.Black,
	onBackground = Color.Black,
	onSurface = Color.Black,
	onError = Color.Black,
)

@Composable
fun TweetSocialTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
	MaterialTheme(
		colors = if (darkTheme) {
			DarkColorPalette
		} else {
			LightColorPalette
		},
		typography = Typography,
		shapes = Shapes,
		content = content
	)
}