package com.alok.twitter.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alok.twitter.R

@Composable
fun NewTweetButton() {
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .size(55.dp),
        shape = CircleShape,
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = MaterialTheme.colors.primary),
        contentPadding = PaddingValues(0.dp),
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_compose),
            null,
            modifier = Modifier.size(26.dp),
            tint = Color.White
        )
    }
}