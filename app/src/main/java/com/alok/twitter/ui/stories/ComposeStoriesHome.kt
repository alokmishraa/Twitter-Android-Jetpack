package com.alok.twitter.ui.home.stories

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alok.twitter.data.Story
import com.alok.twitter.ui.CreateStoryCompose
import com.alok.twitter.ui.common.PHOTO_URL
import com.alok.twitter.ui.theme.TwitterBlue

@Composable
fun ComposeStoriesHome(stories: List<UserStory>) {
    LazyRow {
        item {
            CreateStoryCompose()
        }
        items(stories.size) { index ->
            ComposeUserStory(stories[index])
        }
    }
}

@Composable
fun ComposeUserStory(userStory: UserStory?) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        userStory?.let {
            RoundedUserImage(
                userStory.userImage,
                modifier = Modifier
                    .requiredSize(60.dp)
                    .padding(2.dp)
            )
        } ?: let {
            RoundedUserImage(
                PHOTO_URL,
                modifier = Modifier
                    .requiredSize(60.dp)
                    .padding(2.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        StoryUserName(userStory)
    }
}

@Composable
private fun StoryUserName(userStory: UserStory?) {
    Text(
        userStory?.userName ?: "+ Add",
        modifier = Modifier.fillMaxWidth(0.6f),
        fontSize = 12.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        textAlign = TextAlign.Center
    )
}

@Composable
fun RoundedUserImage(
    url: String, modifier: Modifier = Modifier
        .requiredSize(60.dp)
        .padding(2.dp)
) {
    Surface(
        shape = CircleShape,
        modifier = modifier,
        contentColor = TwitterBlue
    ) {
        AsyncImage(
            url,
            contentDescription = null
        )
    }
}