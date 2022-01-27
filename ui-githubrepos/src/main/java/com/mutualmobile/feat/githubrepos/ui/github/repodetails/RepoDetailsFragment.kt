package com.mutualmobile.feat.githubrepos.ui.github.repodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.findNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.mutualmobile.feat.githubrepos.ui.tweetdetails.TDViewModel
import com.mutualmobile.feat.githubrepos.ui.tweetdetails.TwitterDetailsScreen
import com.mutualmobile.praxis.commonui.theme.TweetifyTheme
import com.mutualmobile.praxis.navigator.ComposeNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A fragment representing a single Repo detail screen.
 */
@AndroidEntryPoint
class RepoDetailsFragment : Fragment() {

    @Inject
    lateinit var navigator: ComposeNavigator

    /**
     * Include a ComposeView directly in a fragment if your full screen is built with Compose,
     * which lets you avoid using an XML layout file entirely.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        // Dispose of the Composition when the view's LifecycleOwner is destroyed
        // DisposeOnViewTreeLifecycleDestroyed: ViewCompositionStrategy that disposes the composition
        // when the ViewTreeLifecycleOwner of the next window the view is attached to is destroyed.
        setViewCompositionStrategy(DisposeOnViewTreeLifecycleDestroyed)

        setContent {
            // Create a Compose MaterialTheme inheriting the existing colors, typography
            // and shapes of the current View system's theme

            val tweetsViewModel: TDViewModel = hiltViewModel()
            TweetifyTheme {
                ProvideWindowInsets {
                    TwitterDetailsScreen(
                        tweetId = arguments?.getString("tweetId"),
                        onBack = { findNavController().navigateUp() },
                        hashTagNavigator = {},
                        tweetViewModel = tweetsViewModel
                    )
                }
            }
        }
    }
}
