package com.praxis.feat.authentication.ui.home.feeds

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praxis.feat.authentication.ui.components.TweetifySurface
import com.praxis.feat.authentication.ui.theme.TweetifyTheme

@Composable
fun ComposeTweetAdvertisementBanner() {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground,
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BannerTitle()
            Spacer(modifier = Modifier.height(8.dp))
            BannerSubtitle()
            Spacer(modifier = Modifier.height(12.dp))
            BannerButton()
        }
    }
}

@Composable
private fun BannerButton() {
    Button(
        onClick = {
        },
        shape = RoundedCornerShape(25),
        colors = ButtonDefaults.buttonColors(backgroundColor = TweetifyTheme.colors.accent)
    ) {
        Text(
            text = "Get started",

            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun BannerSubtitle() {
    Text(
        "Follow relevant accounts with latest information",

        modifier = Modifier.padding(4.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun BannerTitle() {
    Text(
        "Do you need help finding COVID-19 resources?",
        modifier = Modifier.padding(4.dp),

        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 18.sp
    )
}
