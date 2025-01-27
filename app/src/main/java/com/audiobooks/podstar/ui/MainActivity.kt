package com.audiobooks.podstar.ui

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.audiobooks.podstar.data.model.Podcast
import com.audiobooks.podstar.ui.screen.PodcastDetailsScreen
import com.audiobooks.podstar.ui.screen.PodcastListScreen
import com.audiobooks.podstar.ui.theme.PodStarTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContent {
            PodStarTheme {
                PodStarApp()
            }
        }
    }
}

@Serializable
object PodcastListRoute

@Serializable
data class PodcastDetailsRoute(val podcastId: String)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PodStarApp() {
    SharedTransitionLayout {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = PodcastListRoute
        ) {
            composable<PodcastListRoute> {
                PodcastListScreen(
                    navigateToPodcastDetails = {
                        navController.navigate(PodcastDetailsRoute(it))
                    },
                    this@SharedTransitionLayout,
                    this@composable,
                )
            }
            composable<PodcastDetailsRoute> {
                PodcastDetailsScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    this@SharedTransitionLayout,
                    this@composable,
                )
            }
        }
    }
}