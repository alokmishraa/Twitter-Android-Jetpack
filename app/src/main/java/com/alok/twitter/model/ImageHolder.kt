package com.alok.twitter.model

import androidx.compose.ui.graphics.Color
import java.io.File

data class ImageHolder(
	var width: Int = 0,
	var height: Int = 0,
	var colorAverage: Color = Color.White,
	var online: String = "",
	var local: String = ""
) {
	fun getDataForPainter(): Any {
		return if (local != "") File(local) else online
	}
}