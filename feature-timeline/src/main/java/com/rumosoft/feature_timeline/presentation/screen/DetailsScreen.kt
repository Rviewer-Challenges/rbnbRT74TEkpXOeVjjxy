package com.rumosoft.feature_timeline.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rumosoft.feature_timeline.R
import com.rumosoft.feature_timeline.presentation.screen.state.BuildUI
import com.rumosoft.feature_timeline.presentation.viewmodel.DetailsViewModel
import com.rumosoft.feature_timeline.presentation.viewmodel.state.DetailsState
import com.rumosoft.library_components.components.BackNavigationButton
import com.rumosoft.library_components.components.TwitterTopAppBar
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DetailsRoute(
    viewModel: DetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},
    onPictureSelected: (Long, Long) -> Unit = { _, _ -> },
) {
    LaunchedEffect(Unit) {
        viewModel.retrieveTweetDetails()
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DetailsScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onPictureSelected = onPictureSelected,
    )
}

@Composable
fun DetailsScreen(
    uiState: DetailsState,
    onBackClick: () -> Unit = {},
    onPictureSelected: (Long, Long) -> Unit = { _, _ -> },
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TwitterTopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.tweet),
                        style = TwitterMirroringTheme.typography.h3,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                navigationIcon = {
                    BackNavigationButton(onBackClick = onBackClick)
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            uiState.BuildUI(onPictureSelected = onPictureSelected)
        }
    }
}