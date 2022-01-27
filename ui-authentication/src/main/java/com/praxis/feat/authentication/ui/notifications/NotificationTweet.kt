package com.praxis.feat.authentication.ui.notifications

data class NotificationTweet(
    var usrImgUrl: String,
    var title: String,
    var subtitle: String? = null,
    var userName: String,
    val notificationType: NotificationType
)

enum class NotificationType {
    NEW_TWEET,
    FOLLOWED,
    LIKED_REPLY
}
