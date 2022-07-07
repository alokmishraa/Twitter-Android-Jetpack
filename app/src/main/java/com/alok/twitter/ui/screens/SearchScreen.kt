package com.alok.twitter.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen() {
	var value by remember { mutableStateOf("") }

	Box(Modifier.fillMaxSize()) {
		OutlinedTextField(
			value = value, onValueChange = { value = it }, modifier = Modifier
				.align(Alignment.TopCenter)
				.fillMaxWidth()
				.padding(16.dp)
		)
		Text(
			"Search Screen",
			Modifier
				.align(Alignment.Center)
		)
	}
}