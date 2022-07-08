package com.alok.twitter.ui.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alok.twitter.R
import com.alok.twitter.ui.theme.FunctionalRed
import com.alok.twitter.ui.theme.FunctionalRedDark

@Composable
fun NotificationsResults(notificationVM: NotificationsViewModel) {
    val notificationState = notificationVM.notificationsListState

    LazyColumn {
        if (notificationState is NotificationTweetState.Success) {
            items(notificationState.data.size) {
                notificationState.data.forEach { notificationTweet ->
                    ComposeNotificationTweet(notificationTweet)
                }
            }
        }
    }
}

@Composable
fun ComposeNotificationTweet(notificationTweet: NotificationTweet) {
    Column {
        ComposeNotificationTweetInternal(notificationTweet)
        Divider(color = Color.DarkGray, thickness = 0.5.dp)
    }
}

@Composable
private fun ComposeNotificationTweetInternal(notificationTweet: NotificationTweet) {
    Row(modifier = Modifier.padding(start = 24.dp, top = 4.dp, bottom = 4.dp, end = 8.dp)) {
        val resourceId = getResourceId(notificationTweet)
        Icon(
            painterResource(id = resourceId.first),
            contentDescription = null,
            tint = resourceId.second,
            modifier = Modifier
                .requiredSize(50.dp)
                .padding(12.dp),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .requiredSize(60.dp),
                contentColor = MaterialTheme.colors.background
            ) {
                AsyncImage(
                    notificationTweet.usrImgUrl,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            ComposeTextWithBoldUserName(
                notificationTweet.title,
                username = notificationTweet.userName
            )
            Spacer(modifier = Modifier.height(8.dp))
            notificationTweet.subtitle?.let {
                ComposeTextWithBoldUserName(
                    notificationTweet.subtitle!!,
                    username = notificationTweet.userName
                )
            }
        }
    }
}

@Composable
private fun getResourceId(notificationTweet: NotificationTweet) =
    when (notificationTweet.notificationType) {
        NotificationType.FOLLOWED -> {
            Pair(R.drawable.ic_profile, FunctionalRedDark)
        }
        NotificationType.LIKED_REPLY -> {
            Pair(R.drawable.ic_like, FunctionalRed)
        }
        NotificationType.NEW_TWEET -> {
            Pair(R.drawable.ic_notifications, MaterialTheme.colors.background)
        }
    }

@Composable
fun ComposeTextWithBoldUserName(text: String, username: String) {
    Text(text)
}
