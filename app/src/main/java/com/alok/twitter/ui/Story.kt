package com.alok.twitter.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alok.twitter.data.Story


@Composable
fun CreateStoryCompose() {
    Box(
        modifier = Modifier
            .padding(start = 15.dp, end = 18.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .clip(shape = CircleShape),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                println("imageurl = ${Story().profileUrl}")
                AsyncImage(
                    model = Story().profileUrl,
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .width(60.dp)
                        .clip(shape = CircleShape),
                )
            }
            Text(
                "Add",
                fontSize = 14.sp,
                color = MaterialTheme.colors.secondary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .absoluteOffset(38.dp, 36.dp)
                .size(16.dp),
            border = BorderStroke(2.dp, MaterialTheme.colors.background),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
        ) {
            Icon(
                imageVector = Icons.Sharp.Add,
                contentDescription = "Localized description",
                tint = Color.White,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(14.dp)
                    .background(MaterialTheme.colors.primary)
            )
        }

    }
}