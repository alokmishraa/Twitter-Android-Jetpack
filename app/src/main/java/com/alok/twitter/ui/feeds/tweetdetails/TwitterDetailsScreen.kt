package com.alok.twitter.ui.feeds.tweetdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alok.twitter.ui.feeds.ComposeTweet
import com.alok.twitter.ui.feeds.data.Tweet
import com.alok.twitter.ui.feeds.data.TweetState
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun TwitterDetailsScreen(
    tweetId: String?,
    onBack: () -> Unit,
    hashTagNavigator: (String) -> Unit,
    tweetViewModel: TDViewModel
) {
    tweetViewModel.fetchTweetById(tweetId)
    val tweetState = tweetViewModel.tweetByIdState
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        topBar = { TwitterDetailsTopBar(onBack) },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.secondary,
    ) { paddingExtras ->
        if (tweetState is TweetState.SuccessTweet) {
            Surface(modifier = Modifier
                .clickable { onBack.invoke() }
                .padding(paddingExtras)) {
                ComposeTweet(
                    tweet = tweetState.data,
                    onClickTweet = {

                    },
                    hashTagNavigator = hashTagNavigator,
                    onUrlRecognized = { tweet: Tweet, url: String ->
                    }
                )
            }
        }
    }
}

@Composable
fun TwitterDetailsTopBar(onBack: () -> Unit) {
    Surface(
        color = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.secondary,
        elevation = 4.dp
    ) {
        TopAppBar(
            title = {
                Text(text = "Tweet")
            },
            backgroundColor = MaterialTheme.colors.background,
            navigationIcon = {
                IconButton(onClick = {
                    onBack()
                }) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colors.secondary
                    )
                }
            }, elevation = 4.dp
        )
    }
}
