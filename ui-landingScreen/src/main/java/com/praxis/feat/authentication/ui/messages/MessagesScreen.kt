package com.praxis.feat.authentication.ui.messages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mutualmobile.praxis.commonui.theme.AlphaNearOpaque
import com.praxis.feat.authentication.ui.components.ComposeTweetifyFeedText
import com.praxis.feat.authentication.ui.components.TweetifySurface
import com.praxis.feat.authentication.ui.home.feeds.ComposeNameHandlerOverflow
import com.praxis.feat.authentication.ui.home.feeds.ComposeTime
import com.praxis.feat.authentication.ui.home.stories.RoundedUserImage

@Composable
fun MessagesScreen(
    modifierPadding: PaddingValues,
    messagesViewModel: MessagesViewModel = viewModel(),
    onClickMessage: (TwitterMessage) -> Unit
) {
    val messagesList = messagesViewModel.messagesListState

    TweetifySurface(Modifier.padding(modifierPadding)) {
        LazyColumn {
            item {
                if (messagesList is MessagesState.Success) {
                    messagesList.data.forEach { twitterMessage ->
                        MessageItem(twitterMessage, onClickMessage)
                    }
                }
            }
        }
    }
}

@Composable
fun MessageItem(twitterMessage: TwitterMessage, onClickMessage: (TwitterMessage) -> Unit) {
    Column {
        Row(modifier = Modifier.padding(12.dp)) {
            RoundedUserImage(url = twitterMessage.imgUrl)
            Spacer(modifier = Modifier.width(14.dp))
            ComposeMessageColumn(
                twitterMessage,
                onClickMessage
            )
        }
        Divider(color = Color.Gray.copy(AlphaNearOpaque), thickness = 0.5.dp)
    }
}

@Composable
fun ComposeMessageColumn(twitterMessage: TwitterMessage, onClickMessage: (TwitterMessage) -> Unit) {
    Column {
        ComposeNameHandlerOverflow(
            twitterMessage.fullName,
            twitterMessage.userName,
            showOverflow = false
        )
        ComposeTime(twitterMessage.dateTime)
        Spacer(modifier = Modifier.height(8.dp))
        ComposeTweetifyFeedText(
            twitterMessage.lastMessage,
            urlRecognizer = { url ->
            },
            hashTagNavigator = {
            },
            textClick = {
                onClickMessage.invoke(twitterMessage)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
