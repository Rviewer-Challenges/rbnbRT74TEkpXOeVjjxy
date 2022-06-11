package com.rumosoft.feature_timeline.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rumosoft.feature_timeline.presentation.navigation.destination.PicturesDestination
import com.rumosoft.feature_timeline.presentation.navigation.destination.TimelineDestination
import com.rumosoft.feature_timeline.presentation.screen.PicturesRoute
import com.rumosoft.feature_timeline.presentation.screen.TimelineRoute

fun NavGraphBuilder.timelineGraph(navController: NavHostController) {
    composable(route = TimelineDestination.route) {
        TimelineRoute(
            onPictureSelected = { tweetId: Long ->
                navController.navigate("${PicturesDestination.route}/${tweetId}")
            }
        )
    }

    composable(
        route = "${PicturesDestination.route}/{${PicturesDestination.tweetArg}}",
        arguments = listOf(
            navArgument(PicturesDestination.tweetArg) {
                type = NavType.LongType
            }
        )
    ) {
        PicturesRoute()
    }
}
