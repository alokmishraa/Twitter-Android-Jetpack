package com.alok.twitter.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MessageScreen() {
	Box(Modifier.fillMaxSize())
	{
		Text("Message Screen", Modifier.align(Alignment.Center))
	}
}