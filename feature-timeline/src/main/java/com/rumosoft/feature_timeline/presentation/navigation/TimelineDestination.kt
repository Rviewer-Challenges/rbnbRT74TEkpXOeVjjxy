package com.rumosoft.feature_timeline.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.rumosoft.feature_timeline.presentation.navigation.destination.TimelineDestination
import com.rumosoft.feature_timeline.presentation.screen.TimelineRoute

fun NavGraphBuilder.timelineGraph(navController: NavHostController) {
    composable(route = TimelineDestination.route) {
        TimelineRoute()
    }
}