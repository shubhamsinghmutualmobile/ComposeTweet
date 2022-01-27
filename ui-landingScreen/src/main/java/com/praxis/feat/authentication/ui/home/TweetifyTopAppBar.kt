package com.praxis.feat.authentication.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mutualmobile.praxis.commonui.theme.TweetifyTheme
import com.praxis.feat.authentication.R
import com.praxis.feat.authentication.ui.components.TweetifySurface

@Composable
fun TweetifyTopAppBar(shouldShowSearch: Boolean, function: () -> Unit) {

    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.accent,
        elevation = 4.dp
    ) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (shouldShowSearch) {
                        TweetifySurface(
                            color = TweetifyTheme.colors.searchBarBg,
                            shape = RoundedCornerShape(25.dp),
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(
                                "Search Twitter",
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                color = TweetifyTheme.colors.textSecondary,

                                fontSize = 14.sp
                            )
                        }
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_twitter),
                            contentDescription = null
                        )
                    }
                }
            },
            backgroundColor = TweetifyTheme.colors.uiBackground,
            navigationIcon = {
                IconButton(onClick = {
                    function()
                }) {
                    Icon(Icons.Filled.Menu, contentDescription = null)
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        painterResource(id = R.drawable.ic_timeline),
                        contentDescription = null
                    )
                }
            }, elevation = 4.dp
        )
    }
}
