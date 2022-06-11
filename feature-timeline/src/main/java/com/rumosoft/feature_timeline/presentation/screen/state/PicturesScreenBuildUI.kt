package com.rumosoft.feature_timeline.presentation.screen.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.rumosoft.feature_timeline.R
import com.rumosoft.feature_timeline.presentation.viewmodel.state.PicturesLoading
import com.rumosoft.feature_timeline.presentation.viewmodel.state.PicturesReady
import com.rumosoft.feature_timeline.presentation.viewmodel.state.PicturesState
import com.rumosoft.library_components.components.TweetActionButtons
import com.rumosoft.library_components.components.TweetContentImages
import com.rumosoft.library_components.components.TweetImage
import com.rumosoft.library_components.components.model.TweetActionsClick

@Composable
fun PicturesState.BuildUI() {
    when (this) {
        PicturesLoading -> PicturesLoading()
        is PicturesReady -> PicturesReady(
            uiState = this,
        )
    }
}

@Composable
private fun PicturesLoading() {
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
private fun PicturesReady(
    uiState: PicturesReady,
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            TweetImage(
                image = uiState.tweet.images.first(),
                contentDescription = stringResource(id = com.rumosoft.library_components.R.string.tweet_image),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
                onPictureSelected = { },
            )
        }
        TweetActionButtons(
            numComments = uiState.tweet.numComments,
            numRetweets = uiState.tweet.numRetweets,
            numLikes = uiState.tweet.numLikes,
            onActionsClick = object : TweetActionsClick {
                override fun onCommentsClick() {}
                override fun onRetweetsClick() {}
                override fun onLikesClick() {}
                override fun onShareClick() {}
            }
        )
    }
}
