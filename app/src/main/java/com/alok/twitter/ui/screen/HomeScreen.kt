package com.alok.twitter.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.alok.twitter.ui.StoryList
import com.alok.twitter.ui.tweet.TweetList

@Composable
fun HomeScreen() {
    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxHeight()) {
        StoryList()
        Column(
            modifier = Modifier
                .padding(vertical = 0.dp)
        ) {
            TweetList()
        }
    }
}