package com.rumosoft.feature_timeline.presentation.screen.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.rumosoft.feature_timeline.R
import com.rumosoft.feature_timeline.presentation.viewmodel.state.Loading
import com.rumosoft.feature_timeline.presentation.viewmodel.state.Ready
import com.rumosoft.feature_timeline.presentation.viewmodel.state.TimelineState
import com.rumosoft.library_components.components.Tweet
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun TimelineState.BuildUI() {
    when (this) {
        Loading -> TimelineLoading()
        is Ready -> TimelineReady(this)
    }
}

@Composable
private fun TimelineLoading() {
    val loadingDescription = stringResource(id = R.string.loading)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = loadingDescription }
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun TimelineReady(
    uiState: Ready,
) {
    LazyColumn {
        items(items = uiState.tweets, itemContent = { tweet ->
            Tweet(
                profileImageUrl = tweet.profileImageUrl,
                username = tweet.username,
                nickname = tweet.nickname,
                message = tweet.message,
                elapsedTime = tweet.elapsedTime,
                numComments = tweet.numComments,
                numRetweets = tweet.numRetweets,
                numLikes = tweet.numLikes,
                verified = tweet.verified,
            )
            Divider(color = TwitterMirroringTheme.colors.secondaryVariant, thickness = 1.dp)
        })
    }
}
