package com.alok.twitter.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object ColorUtils {
	@Composable
	fun Color.grayed(): Color {
		return copy(alpha = 0.5f)
	}
}