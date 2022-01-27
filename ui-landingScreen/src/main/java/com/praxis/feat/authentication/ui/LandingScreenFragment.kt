package com.praxis.feat.authentication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.mutualmobile.praxis.commonui.theme.TweetifyTheme
import com.mutualmobile.praxis.navigator.ComposeNavigator
import com.mutualmobile.praxis.navigator.FragmentNavGraphNavigator
import com.praxis.feat.authentication.ui.home.TweetifyScaffold
import com.praxis.feat.authentication.ui.utils.SystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A fragment representing a single Auth screen.
 */
@AndroidEntryPoint
class LandingScreenFragment : Fragment() {

    @Inject
    lateinit var navigatorFragment: FragmentNavGraphNavigator

    @Inject
    lateinit var composeNavigator: ComposeNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {

        // Dispose of the Composition when the view's LifecycleOwner is destroyed
        setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            TweetifyTheme {
                SystemUiController(requireActivity().window).setStatusBarColor(TweetifyTheme.colors.statusBarColor)

                LaunchedEffect(Unit) {
                    navigatorFragment.handleNavigationCommands(findNavController())
                }
                /**
                 * Make the bridge between Compose and the fragment-based Navigation component
                 * by finding the NavController and navigating to the destination:
                 */
                ProvideWindowInsets {
                    TweetifyScaffold(findNavController())
                }
            }
        }
    }
}
