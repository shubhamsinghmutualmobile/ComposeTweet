package com.praxis.feat.authentication.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.praxis.feat.authentication.ui.home.TweetifyScaffold
import com.praxis.feat.authentication.ui.theme.TweetifyTheme

@Composable
fun AuthenticationUI() {
    TweetifyTheme {
        TweetifyScaffold()
    }
}

@Preview("Light+Dark")
@Composable
fun PreviewAuth() {
    PraxisTheme(darkTheme = true) {
        AuthenticationUI()
    }
}
