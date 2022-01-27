package com.praxis.feat.authentication.ui.home.stories

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.mutualmobile.praxis.commonui.theme.TweetifyTheme
import com.praxis.feat.authentication.ui.components.TweetifySurface
import com.praxis.feat.authentication.ui.utils.PHOTO_URL

@Composable
fun ComposeStoriesHome(stories: List<UserStory>) {
    LazyRow {
        item {
            ComposeUserStory(null)
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
                    .border(3.dp, TweetifyTheme.colors.accent, CircleShape)
            )
        } ?: let {
            RoundedUserImage(
                PHOTO_URL
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        StoryUserName(userStory)
    }
}

@Composable
private fun StoryUserName(userStory: UserStory?) {
    Text(
        userStory?.userName ?: "Add",
        modifier = Modifier.fillMaxWidth(0.6f),
        fontSize = 12.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        textAlign = TextAlign.Center
    )
}

@Composable
fun RoundedUserImage(
    url: String,
    modifier: Modifier = Modifier
        .requiredSize(60.dp)
        .padding(2.dp)
) {
    TweetifySurface(
        shape = CircleShape,
        modifier = modifier,
        contentColor = TweetifyTheme.colors.uiBackground
    ) {
        Image(
            rememberImagePainter(url),
            contentDescription = null
        )
    }
}
