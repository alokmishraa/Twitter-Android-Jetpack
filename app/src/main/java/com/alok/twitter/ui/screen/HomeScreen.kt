package com.mutualmobile.tweetify.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alok.twitter.ui.common.SwipeProgressIndicator
import com.alok.twitter.ui.feeds.ComposeTweet
import com.alok.twitter.ui.feeds.ComposeTweetAdvertisementBanner
import com.alok.twitter.ui.feeds.data.Tweet
import com.alok.twitter.ui.feeds.data.TweetState
import com.alok.twitter.ui.feeds.data.TweetsViewModel
import com.alok.twitter.ui.home.stories.ComposeStoriesHome
import com.alok.twitter.ui.home.stories.UserStoriesRepository
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import timber.log.Timber

@Composable
fun HomeScreen(
    tweetsViewModel: TweetsViewModel = hiltViewModel()
) {
    val tweetState = tweetsViewModel.tweetsState
    val swipeRefreshState = rememberSwipeRefreshState(tweetState is TweetState.Loading)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            swipeEnabled = tweetState !is TweetState.Loading,
            indicator = { state, trigger ->
                SwipeProgressIndicator(
                    swipeRefreshState = state,
                    refreshTriggerDistance = trigger
                )
            },
            onRefresh = {
                Timber.e("fetch latest")
                tweetsViewModel.fetchLatest()
            }
        ) {
            LazyColumn {
                item {
                    ComposeStoriesWithSpacing()
                }
                item {
                    ComposeTweetADBanner()
                }
                when (tweetState) {

                    is TweetState.Success -> {
                        item {
                            tweetState.data.forEach {
                                ComposeTweet(
                                    tweet = it, onClickTweet =
                                    { tweet ->
                                        // TODO need to implement
                                    }, hashTagNavigator = { hashTag ->
                                        // TODO need to implement
                                    }, onUrlRecognized = { tweet: Tweet, url: String ->
                                        tweetsViewModel.loadMetadata(tweet, url)
                                    }
                                )
                            }
                        }
                    }
                    else -> {
                    }
                }
            }
        }
    }
}

@Composable
private fun ComposeTweetADBanner() {
    Column {
        Divider(
            color = Color.Gray,
            thickness = 5.dp
        )
        ComposeTweetAdvertisementBanner()
        Divider(
            color = Color.Gray,
            thickness = 5.dp
        )
    }
}

@Composable
private fun ComposeStoriesWithSpacing() {
    Column {
        Spacer(modifier = Modifier.height(2.dp))
        ComposeStoriesHome(UserStoriesRepository.fetchStories())
        Spacer(modifier = Modifier.height(2.dp))
    }
}



