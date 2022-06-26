package com.rumosoft.feature_timeline.presentation.screen.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
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
import com.rumosoft.feature_timeline.presentation.screen.model.TweetUI
import com.rumosoft.feature_timeline.presentation.viewmodel.state.CommentsState
import com.rumosoft.feature_timeline.presentation.viewmodel.state.DetailsState
import com.rumosoft.feature_timeline.presentation.viewmodel.state.TweetState
import com.rumosoft.library_components.components.TweetFullWidth
import com.rumosoft.library_components.components.TweetTimeline
import com.rumosoft.library_components.components.model.TweetActionsClick
import com.rumosoft.library_components.infrastructure.toast
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun DetailsState.BuildUI(
    onPictureSelected: (Long, Long) -> Unit = { _, _ -> },
) {
    when (tweet) {
        TweetState.Loading -> DetailsLoading()
        is TweetState.Ready ->
            DetailsReady(
                tweetState = tweet,
                commentsState = comments,
                onPictureSelected = onPictureSelected,
            )
    }
}

@Composable
private fun DetailsLoading() {
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
private fun DetailsReady(
    tweetState: TweetState.Ready,
    commentsState: CommentsState,
    onPictureSelected: (Long, Long) -> Unit = { _, _ -> },
) {
    val context = LocalContext.current
    val tweet = tweetState.tweet
    LazyColumn {
        item {
            TweetFullWidth(
                tweetId = tweet.id,
                profileImageUrl = tweet.profileImageUrl,
                username = tweet.username,
                nickname = tweet.nickname,
                message = tweet.message,
                elapsedTime = tweet.longElapsedTime,
                numComments = tweet.numComments,
                numRetweets = tweet.numRetweets,
                numQuoteTweets = tweet.numQuoteTweets,
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
                onPictureSelected = onPictureSelected,
            )
            Divider(color = TwitterMirroringTheme.colors.secondaryVariant, thickness = 1.dp)
            when (commentsState) {
                CommentsState.Loading -> CommentsLoading()
                is CommentsState.Ready ->
                    CommentsReady(
                        comments = commentsState.comments,
                    )
            }
        }
    }
}

@Composable
private fun CommentsLoading() {
    val loadingDescription = stringResource(id = R.string.loading_comments)
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
fun CommentsReady(
    comments: List<TweetUI>,
) {
    comments.forEach { tweet ->
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
        )
    }
}
