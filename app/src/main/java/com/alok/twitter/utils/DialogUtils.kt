package com.alok.twitter.utils

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun NotImplementedAlert(onDismiss: () -> Unit) {
	AlertDialog(
		onDismissRequest = onDismiss,
		text = {
			Text(
				text = "Not implemented yet \uD83D\uDC77",
				style = MaterialTheme.typography.body2
			)
		},
		confirmButton = {
			TextButton(onClick = onDismiss) {
				Text(text = "CLOSE")
			}
		}
	)
}