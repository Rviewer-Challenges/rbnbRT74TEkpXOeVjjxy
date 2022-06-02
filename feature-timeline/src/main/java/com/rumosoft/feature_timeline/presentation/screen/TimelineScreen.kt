package com.rumosoft.feature_timeline.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun TimelineRoute() {
    TimelineScreen()
}

@Composable
fun TimelineScreen() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Timeline",
            style = TwitterMirroringTheme.typography.h1
        )
    }
}