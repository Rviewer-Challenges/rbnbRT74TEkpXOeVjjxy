package com.rumosoft.feature_timeline.presentation.screen

import androidx.compose.foundation.layout.Column
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
import com.rumosoft.feature_timeline.presentation.viewmodel.PicturesViewModel
import com.rumosoft.feature_timeline.presentation.viewmodel.state.PicturesState
import com.rumosoft.library_components.components.BackNavigationButton
import com.rumosoft.library_components.components.TwitterTopAppBar

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PicturesRoute(
    viewModel: PicturesViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},
) {
    LaunchedEffect(Unit) {
        viewModel.retrievePictures()
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PicturesScreen(uiState, onBackClick)
}

@Composable
fun PicturesScreen(
    uiState: PicturesState,
    onBackClick: () -> Unit = {},
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TwitterTopAppBar(
                navigationIcon = {
                    BackNavigationButton(onBackClick = onBackClick)
                }
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            Column {
                uiState.BuildUI()
            }
        }
    }
}