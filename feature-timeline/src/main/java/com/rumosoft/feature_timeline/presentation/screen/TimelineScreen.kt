package com.rumosoft.feature_timeline.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rumosoft.feature_timeline.presentation.screen.state.BuildUI
import com.rumosoft.feature_timeline.presentation.viewmodel.TimelineViewModel
import com.rumosoft.feature_timeline.presentation.viewmodel.state.TimelineState

@Composable
fun TimelineRoute(
    viewModel: TimelineViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.retrieveTimeline()
    }
    val uiState by viewModel.uiState.collectAsState()
    TimelineScreen(uiState)
}

@Composable
fun TimelineScreen(
    uiState: TimelineState
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        uiState.BuildUI()
    }
}