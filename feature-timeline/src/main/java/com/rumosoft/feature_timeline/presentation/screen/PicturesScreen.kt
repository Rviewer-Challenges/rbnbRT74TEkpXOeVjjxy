package com.rumosoft.feature_timeline.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rumosoft.feature_timeline.presentation.screen.state.BuildUI
import com.rumosoft.feature_timeline.presentation.viewmodel.PicturesViewModel
import com.rumosoft.feature_timeline.presentation.viewmodel.state.PicturesState
import com.rumosoft.library_components.components.TweetActionButtons

@Composable
fun PicturesRoute(
    viewModel: PicturesViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.retrievePictures()
    }
    val uiState by viewModel.uiState.collectAsState()
    PicturesScreen(uiState)
}

@Composable
fun PicturesScreen(
    uiState: PicturesState
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            uiState.BuildUI()
        }
    }
}