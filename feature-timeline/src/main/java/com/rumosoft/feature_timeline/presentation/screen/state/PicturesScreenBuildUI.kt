package com.rumosoft.feature_timeline.presentation.screen.state

import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.viewinterop.AndroidView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.rumosoft.feature_timeline.R
import com.rumosoft.feature_timeline.presentation.viewmodel.state.PicturesLoading
import com.rumosoft.feature_timeline.presentation.viewmodel.state.PicturesReady
import com.rumosoft.feature_timeline.presentation.viewmodel.state.PicturesState
import com.rumosoft.library_components.components.TweetActionButtons
import com.rumosoft.library_components.components.TweetImage
import com.rumosoft.library_components.components.model.ImageTypeUI
import com.rumosoft.library_components.components.model.ImageUI
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
    val context = LocalContext.current
    val image = remember {
        uiState.tweet.images.find { it.id == uiState.pictureId }
            ?: uiState.tweet.images.first()
    }
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }.build()
    }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            if (!isVideo(image)) {
                TweetImage(
                    image = image,
                    contentDescription = stringResource(id = com.rumosoft.library_components.R.string.tweet_image),
                    contentScale = ContentScale.FillWidth,
                    imageLoader = imageLoader,
                    modifier = Modifier.fillMaxWidth(),
                    onPictureSelected = { },
                )
            } else {
                VideoPlayer(image.url)
            }
        }
        Surface(
            color = MaterialTheme.colors.surface.copy(alpha = 0.9f)
        ) {
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
}

private fun isVideo(image: ImageUI) =
    image.imageType == ImageTypeUI.Video

@Composable
fun VideoPlayer(videoUrl: String) {
    val context = LocalContext.current
    val player = ExoPlayer.Builder(context).build()
    val playerView = StyledPlayerView(context)
    val mediaItem = MediaItem.fromUri(videoUrl)
    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }
    player.setMediaItem(mediaItem)
    playerView.player = player
    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = playWhenReady

    }
    AndroidView(factory = {
        playerView
    })
}
