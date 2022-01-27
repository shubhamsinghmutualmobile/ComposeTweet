package com.praxis.feat.authentication.ui.home.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praxis.feat.authentication.ui.components.TweetifySurface
import com.praxis.feat.authentication.ui.theme.TweetifyTheme

val drawerNavigationItems = listOf(
    DrawerNavigationScreens.Profile,
    DrawerNavigationScreens.Lists,
    DrawerNavigationScreens.Topics,
    DrawerNavigationScreens.Bookmarks,
    DrawerNavigationScreens.Moments,
)

@Composable
fun DrawerMenuOptions() {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            drawerNavigationItems.forEachIndexed { index, screen ->
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavigationIcon(screen)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(screen.route, fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
private fun NavigationIcon(
    screen: DrawerNavigationScreens
) {
    Icon(
        painterResource(screen.icon),
        contentDescription = null,
        modifier = Modifier.size(28.dp),
    )
}

@Preview("Light")
@Composable
fun DrawerMenuOptionsPreview() {
    TweetifyTheme {
        DrawerMenuOptions()
    }
}

@Preview("Light+Dark")
@Composable
fun DrawerMenuOptionsPreviewDark() {
    TweetifyTheme(darkTheme = true) {
        DrawerMenuOptions()
    }
}
