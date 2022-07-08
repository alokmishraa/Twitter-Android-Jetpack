package com.alok.twitter.ui.notifications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor() : ViewModel() {

    var notificationsListState by mutableStateOf<NotificationTweetState>(NotificationTweetState.Loading)
        private set

    init {
        val notifications = prepareNotifiations()
        notificationsListState = NotificationTweetState.Success(notifications)
    }

    private fun prepareNotifiations(): List<NotificationTweet> {
        val notifications = mutableListOf<NotificationTweet>()
        notifications.add(
            NotificationTweet(
                usrImgUrl = "https://images.mktw.net/im-311693?width=620&size=1.4382022471910112",
                title = "New Tweet notifications for Eion Mask",
                userName = "Eion Mask",
                notificationType = NotificationType.NEW_TWEET
            )
        )
        notifications.add(
            NotificationTweet(
                usrImgUrl = "https://graph.facebook.com/945387882269493/picture?type=small",
                title = "Narendra Modi followed you",
                userName = "Narendra Modi",
                notificationType = NotificationType.FOLLOWED
            )
        )
        notifications.add(
            NotificationTweet(
                usrImgUrl = "https://graph.facebook.com/945387882269493/picture?type=small",
                title = "Shinjo Abe liked your reply",
                subtitle = "Google\nis awesome",
                userName = "Shinjo Abe",
                notificationType = NotificationType.LIKED_REPLY,
            )
        )
        return notifications
    }
}
