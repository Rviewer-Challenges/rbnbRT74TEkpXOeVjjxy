package com.rumosoft.library_components.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun TweetStackedHeader(
    message: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = message,
        color = TwitterMirroringTheme.colors.onBackground,
        style = TwitterMirroringTheme.typography.body1,
        modifier = modifier,
    )
}