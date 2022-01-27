package com.praxis.feat.authentication.ui.notifications

sealed class NotificationTweetState {
    object Loading : NotificationTweetState()
    class Success(val data: List<NotificationTweet>) : NotificationTweetState()
}
