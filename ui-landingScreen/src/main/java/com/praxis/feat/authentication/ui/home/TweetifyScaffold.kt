package com.praxis.feat.authentication.ui.home

import androidx.compose.material.DrawerDefaults
import androidx.compose.material.DrawerValue
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.praxis.commonui.theme.TweetifyTheme
import com.praxis.feat.authentication.ui.home.bottomnavigation.TweetifyHomeBottomAppBar
import com.praxis.feat.authentication.ui.home.drawer.TweetifyHomeDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TweetifyScaffold(navigatorFragment: NavController) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val (shouldShowAppBar, updateAppBarVisibility) = remember { mutableStateOf(true) }
    val (shouldShowSearch, updateSearchBarVisibility) = remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val navActions = remember(navController) {
        MainActions(
            navController,
            updateAppBarVisibility,
            scaffoldState,
            coroutineScope,
            updateSearchBarVisibility
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            TweetifyHomeTopBar(
                scaffoldState,
                shouldShowAppBar,
                coroutineScope,
                shouldShowSearch
            )
        },
        drawerContent = { TweetifyHomeDrawer() },
        bottomBar = {
            TweetifyHomeBottomAppBar(
                navActions.switchBottomTab,
                navController,
                shouldShowAppBar
            )
        },
        drawerShape = MaterialTheme.shapes.medium,
        drawerElevation = DrawerDefaults.Elevation,
        drawerBackgroundColor = TweetifyTheme.colors.uiBackground,
        drawerContentColor = TweetifyTheme.colors.textSecondary,
        drawerScrimColor = TweetifyTheme.colors.uiBorder,
        backgroundColor = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.textSecondary
    ) { padding ->
        TweetifyNavigationHost(
            navController,
            padding,
            shouldShowAppBar = updateAppBarVisibility,
            navAction = navActions,
            shouldShowSearchBar = updateSearchBarVisibility,
            navigatorFragment = navigatorFragment
        )
    }
}

@Composable
private fun TweetifyHomeTopBar(
    scaffoldState: ScaffoldState,
    shouldShowAppBar: Boolean,
    scope: CoroutineScope,
    shouldShowSearch: Boolean
) {
    if (shouldShowAppBar) {
        TweetifyTopAppBar(shouldShowSearch = shouldShowSearch) {
            scope.launch {
                if (scaffoldState.drawerState.isOpen) {
                    scaffoldState.drawerState.close()
                } else {
                    scaffoldState.drawerState.open()
                }
            }
        }
    }
}
