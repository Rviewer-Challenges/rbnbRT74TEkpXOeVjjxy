package com.rumosoft.feature_timeline.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rumosoft.feature_timeline.presentation.screen.state.BuildUI
import com.rumosoft.feature_timeline.presentation.viewmodel.TimelineViewModel
import com.rumosoft.feature_timeline.presentation.viewmodel.state.TimelineState
import com.rumosoft.library_components.components.TwitterTitle
import com.rumosoft.library_components.components.TwitterTopAppBar

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun TimelineRoute(
    viewModel: TimelineViewModel = hiltViewModel(),
    onTweetSelected: (Long) -> Unit = { _ -> },
    onPictureSelected: (Long, Long) -> Unit = { _, _ -> },
) {
    LaunchedEffect(Unit) {
        viewModel.retrieveTimeline()
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TimelineScreen(
        uiState = uiState,
        onTweetSelected = onTweetSelected,
        onPictureSelected = onPictureSelected,
    )
}

@Composable
fun TimelineScreen(
    uiState: TimelineState,
    onTweetSelected: (Long) -> Unit = { _ -> },
    onPictureSelected: (Long, Long) -> Unit = { _, _ -> },
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TwitterTopAppBar(
                title = { TwitterTitle() }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            uiState.BuildUI(onTweetSelected, onPictureSelected)
        }
    }
}