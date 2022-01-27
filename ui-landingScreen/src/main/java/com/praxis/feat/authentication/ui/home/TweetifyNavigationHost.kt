package com.praxis.feat.authentication.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.praxis.feat.authentication.ui.home.DestinationsArguments.HASH_TAG_KEY
import com.praxis.feat.authentication.ui.home.bottomnavigation.BottomNavigationScreens
import com.praxis.feat.authentication.ui.home.bottomnavigation.TwitterNavigationScreen
import com.praxis.feat.authentication.ui.home.feeds.data.TweetsViewModel
import com.praxis.feat.authentication.ui.messages.MessagesScreen
import com.praxis.feat.authentication.ui.notifications.NotificationScreen
import com.praxis.feat.authentication.ui.search.SearchScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TweetifyNavigationHost(
    navController: NavHostController,
    padding: PaddingValues,
    shouldShowAppBar: (Boolean) -> Unit,
    navAction: MainActions,
    shouldShowSearchBar: (Boolean) -> Unit,
    navigatorFragment: NavController,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Home.route,
    ) {
        bottomTabs(navAction, padding, shouldShowSearchBar, navigatorFragment)
    }
}

private fun NavGraphBuilder.bottomTabs(
    actions: MainActions,
    padding: PaddingValues,
    shouldShowSearchBar: (Boolean) -> Unit,
    navigatorFragment: NavController
) {
    composable(BottomNavigationScreens.Home.route) { backStack ->
        shouldShowSearchBar(false)
        val tweetsViewModel: TweetsViewModel = hiltViewModel()
        HomeScreen(
            navigateToTweet = { tweetId ->
                actions.navigateToTweet(tweetId, backStack)
            },
            modifierPadding = padding,
            navigateToHashTagSearch = { hashTagSearchParam ->
                actions.navigateToSearch(hashTagSearchParam)
            }, tweetsViewModel, navigatorFragment
        )
        BackHandler {
            actions.drawerCheck()
        }
    }

    composable(
        "${BottomNavigationScreens.Search.route}/{$HASH_TAG_KEY}",
        arguments = listOf(navArgument(HASH_TAG_KEY) { type = NavType.StringType })
    ) {
        shouldShowSearchBar(true)
        SearchScreen(
            modifierPadding = padding,
            hashTagParam = it.arguments?.getString(HASH_TAG_KEY)
        )
        BackHandler {
            actions.drawerCheck()
        }
    }

    composable(BottomNavigationScreens.Search.route) {
        shouldShowSearchBar(true)
        SearchScreen(
            modifierPadding = padding,
            hashTagParam = it.arguments?.getString(HASH_TAG_KEY)
        )
        BackHandler {
            actions.drawerCheck()
        }
    }

    composable(BottomNavigationScreens.Notifications.route) {
        shouldShowSearchBar(false)

        NotificationScreen(modifierPadding = padding)
        BackHandler {
            actions.drawerCheck()
        }
    }
    composable(BottomNavigationScreens.Messages.route) {
        shouldShowSearchBar(false)

        MessagesScreen(modifierPadding = padding, onClickMessage = {
        })
        BackHandler {
            actions.drawerCheck()
        }
    }
}

object DestinationsArguments {
    const val TWEET_ID_KEY = "tweetId"
    const val HASH_TAG_KEY = "hashTagParam"
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(
    private val navController: NavHostController,
    private val shouldShowAppBar: (Boolean) -> Unit,
    private val scaffoldState: ScaffoldState,
    private val scope: CoroutineScope,
    private val shouldShowSearchBar: (Boolean) -> Unit,
) {

    fun drawerCheck() {
        if (scaffoldState.drawerState.isOpen) {
            scope.launch {
                scaffoldState.drawerState.close()
            }
        } else {
            navController.navigateUp()
        }
    }

    val navigateToTweet = { tweetId: String, from: NavBackStackEntry ->
        if (from.lifecycleIsResumed()) {
            shouldShowAppBar(false)
            navController.navigate(route = "${TwitterNavigationScreen.TweetDetailScreen.route}/$tweetId")
        }
    }

    fun navigateToSearch(hashTag: String, navigateHomeFirst: Boolean = false) {
        shouldShowSearchBar(true)
        if (navigateHomeFirst) {
            while (!navController.navigateUp()) {
                navController.popBackStack()
            }
            shouldShowAppBar(true)
            navController.navigate(route = "${BottomNavigationScreens.Search.route}/$hashTag") {
                popUpTo(navController.graph.startDestinationId)
            }
        } else {
            navController.navigate(route = "${BottomNavigationScreens.Search.route}/$hashTag") {
                popUpTo(navController.graph.startDestinationId)
            }
        }
    }

    val switchBottomTab = { tabRoute: String, currentRoute: String ->
        if (tabRoute != currentRoute) {
            navController.navigate(route = tabRoute) {
                popUpTo(navController.graph.startDestinationId)
                // Avoid multiple copies of the same destination when re-selecting the same item
                launchSingleTop = true
            }
        }
    }

    fun upPress(from: NavBackStackEntry, shouldShowAppBar: (Boolean) -> Unit) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            shouldShowAppBar(true)
            navController.navigateUp()
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
