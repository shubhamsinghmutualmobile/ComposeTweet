package com.mutualmobile.feat.githubrepos.ui.tweetdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.praxis.commonui.theme.TweetifyTheme
import com.praxis.feat.authentication.ui.components.TweetifySurface
import com.praxis.feat.authentication.ui.home.feeds.ComposeTweet
import com.praxis.feat.authentication.ui.home.feeds.data.Tweet
import com.praxis.feat.authentication.ui.home.feeds.data.TweetState

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
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = { TwitterDetailsTopBar(onBack) },
        backgroundColor = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.textSecondary,
    ) { paddingExtras ->
        if (tweetState is TweetState.SuccessTweet) {
            TweetifySurface(
                modifier = Modifier
                    .clickable { onBack.invoke() }
                    .padding(paddingExtras)
            ) {
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
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.accent,
        elevation = 4.dp
    ) {
        TopAppBar(
            title = {
                Text(text = "Tweet")
            },
            backgroundColor = TweetifyTheme.colors.uiBackground,
            navigationIcon = {
                IconButton(onClick = {
                    onBack()
                }) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = TweetifyTheme.colors.accent
                    )
                }
            }, elevation = 4.dp
        )
    }
}
