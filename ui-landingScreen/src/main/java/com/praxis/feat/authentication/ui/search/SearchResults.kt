package com.praxis.feat.authentication.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mutualmobile.praxis.commonui.theme.AlphaNearOpaque
import com.mutualmobile.praxis.commonui.theme.AlphaNearTransparent
import com.mutualmobile.praxis.commonui.theme.TweetifyTheme
import com.praxis.feat.authentication.R

@Composable
fun SearchResults(searchVM: SearchTabViewModel) {
    val searchItems = searchVM.searchTabState
    val searchHeader = searchVM.searchHeaderState

    LazyColumn {
        if (searchHeader != null) {
            item {
                ComposeSearchHeader(searchHeader)
            }
        }
        item {
            Divider(
                color = TweetifyTheme.colors.uiBorder.copy(AlphaNearTransparent),
                thickness = 5.dp
            )
        }
        if (searchItems is SearchState.Success) {
            items(searchItems.searchData.size) {
                searchItems.searchData.forEach { searchTwitter ->
                    ComposeSearchItem(searchTwitter)
                }
            }
        }
    }
}

@Composable
fun ComposeSearchItem(searchTwitter: SearchTwitter) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LeftContent(searchTwitter)
            OtherEnd(searchTwitter)
        }
        Divider(color = Color.Gray.copy(AlphaNearOpaque), thickness = 0.5.dp)
    }
}

@Composable
private fun LeftContent(searchTwitter: SearchTwitter) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(8.dp)
    ) {
        Text(
            searchTwitter.searchCategory, fontWeight = FontWeight.Bold,
            color = TweetifyTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            searchTwitter.hashTagTitle,
            color = TweetifyTheme.colors.textSecondary,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            searchTwitter.totalTweets,
            color = TweetifyTheme.colors.textPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun OtherEnd(searchTwitter: SearchTwitter) {
    if (searchTwitter.imageUrl == null) {
        Icon(
            painterResource(id = R.drawable.ic_vector_overflow),
            modifier = Modifier
                .padding(12.dp),
            contentDescription = null,
            tint = Color.Gray
        )
    } else {
        Image(
            rememberImagePainter(searchTwitter.imageUrl),
            modifier = Modifier
                .padding(12.dp),
            contentDescription = null
        )
    }
}
