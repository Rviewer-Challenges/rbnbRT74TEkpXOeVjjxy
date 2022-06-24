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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.rumosoft.feature_timeline.R
import com.rumosoft.feature_timeline.presentation.viewmodel.state.Loading
import com.rumosoft.feature_timeline.presentation.viewmodel.state.Ready
import com.rumosoft.feature_timeline.presentation.viewmodel.state.TimelineState
import com.rumosoft.library_components.components.TweetTimeline
import com.rumosoft.library_components.components.model.TweetActionsClick
import com.rumosoft.library_components.infrastructure.toast
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun TimelineState.BuildUI(
    onTweetSelected: (Long) -> Unit = { _ -> },
    onPictureSelected: (Long, Long) -> Unit = { _, _ -> }
) {
    when (this) {
        Loading -> TimelineLoading()
        is Ready -> TimelineReady(
            uiState = this,
            onTweetSelected = onTweetSelected,
            onPictureSelected = onPictureSelected,
        )
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
    onTweetSelected: (Long) -> Unit = { _ -> },
    onPictureSelected: (Long, Long) -> Unit = { _, _ -> },
) {
    val context = LocalContext.current
    val timelineContentDescription = stringResource(id = R.string.timeline)
    LazyColumn(
        modifier = Modifier.semantics {
            contentDescription = timelineContentDescription
        }
    ) {
        items(items = uiState.tweets, itemContent = { tweet ->
            TweetTimeline(
                tweetId = tweet.id,
                profileImageUrl = tweet.profileImageUrl,
                username = tweet.username,
                nickname = tweet.nickname,
                message = tweet.message,
                elapsedTime = tweet.shortElapsedTime,
                numComments = tweet.numComments,
                totalRetweets = tweet.totalRetweets,
                numLikes = tweet.numLikes,
                verified = tweet.verified,
                images = tweet.images,
                onActionsClick = object : TweetActionsClick {
                    override fun onCommentsClick() {
                        context.toast("Comments click")
                    }

                    override fun onRetweetsClick() {
                        context.toast("Retweets click")
                    }

                    override fun onLikesClick() {
                        context.toast("Likes click")
                    }

                    override fun onShareClick() {
                        context.toast("Share click")
                    }
                },
                onTweetSelected = onTweetSelected,
                onPictureSelected = onPictureSelected,
            )
            Divider(color = TwitterMirroringTheme.colors.secondaryVariant, thickness = 1.dp)
        })
    }
}
