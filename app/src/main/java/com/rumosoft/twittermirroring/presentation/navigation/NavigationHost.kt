package com.rumosoft.twittermirroring.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rumosoft.feature_timeline.presentation.navigation.destination.TimelineDestination
import com.rumosoft.feature_timeline.presentation.navigation.timelineGraph

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController,
        startDestination = TimelineDestination.route,
        modifier = modifier
    ) {
        timelineGraph(navController)
    }
}
