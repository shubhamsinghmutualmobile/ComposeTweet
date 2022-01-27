package com.praxis.feat.authentication.ui.home

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mutualmobile.praxis.commonui.theme.AlphaNearTransparent
import com.mutualmobile.praxis.commonui.theme.TweetifyTheme
import com.praxis.feat.authentication.R
import com.praxis.feat.authentication.ui.components.TweetifySurface
import com.praxis.feat.authentication.ui.components.custom.SwipeProgressIndicator
import com.praxis.feat.authentication.ui.home.feeds.ComposeTweet
import com.praxis.feat.authentication.ui.home.feeds.ComposeTweetAdvertisementBanner
import com.praxis.feat.authentication.ui.home.feeds.data.Tweet
import com.praxis.feat.authentication.ui.home.feeds.data.TweetState
import com.praxis.feat.authentication.ui.home.feeds.data.TweetsViewModel
import com.praxis.feat.authentication.ui.home.stories.ComposeStoriesHome
import com.praxis.feat.authentication.ui.home.stories.UserStoriesRepository
import timber.log.Timber

@Composable
fun HomeScreen(
    navigateToTweet: (String) -> Unit?,
    modifierPadding: PaddingValues,
    navigateToHashTagSearch: (String) -> Unit?,
    tweetsViewModel: TweetsViewModel,
    navigatorFragment: NavController
) {
    val tweetState = tweetsViewModel.tweetsState
    val swipeRefreshState = rememberSwipeRefreshState(tweetState is TweetState.Loading)

    TweetifySurface(
        modifier = Modifier
            .fillMaxSize()
            .padding(modifierPadding)
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
                                    tweet = it,
                                    onClickTweet =
                                    { tweet ->
                                        val bundle = Bundle()
                                        bundle.putString("tweetId", tweet.tUid)
                                        navigatorFragment.navigate(
                                            R.id.repo_details_fragment,
                                            bundle
                                        )
//                                        navigateToTweet(tweet.tUid)
                                    },
                                    hashTagNavigator = { hashTag ->
                                        navigateToHashTagSearch.invoke(hashTag)
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
            color = TweetifyTheme.colors.uiBorder.copy(AlphaNearTransparent),
            thickness = 5.dp
        )
        ComposeTweetAdvertisementBanner()
        Divider(
            color = TweetifyTheme.colors.uiBorder.copy(AlphaNearTransparent),
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
