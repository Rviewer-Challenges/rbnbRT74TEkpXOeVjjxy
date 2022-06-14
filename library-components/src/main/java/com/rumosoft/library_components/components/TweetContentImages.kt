package com.rumosoft.library_components.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.Coil
import coil.ImageLoader
import coil.decode.VideoFrameDecoder
import com.rumosoft.library_components.R
import com.rumosoft.library_components.components.model.ImageTypeUI.Gif
import com.rumosoft.library_components.components.model.ImageTypeUI.Video
import com.rumosoft.library_components.components.model.ImageUI
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

private const val IMAGE_SEPARATION_DP = 4
private const val MULTIPLE_PICS_COLUMN_ASPECT_RATIO = 0.9f

@Composable
fun TweetContentImages(
    images: List<ImageUI>,
    onPictureSelected: (Long) -> Unit = {},
) {
    if (images.isNotEmpty()) {
        val imagesContentDescription = stringResource(id = R.string.tweet_images)
        Box(
            modifier = Modifier
                .padding(top = 7.dp)
                .padding(horizontal = TwitterMirroringTheme.paddings.medium)
                .fillMaxWidth()
                .semantics { contentDescription = imagesContentDescription }
                .clip(
                    RoundedCornerShape(10.dp)
                )
        ) {
            when (images.size) {
                1 -> OneImageTweetLayout(
                    image = images.first(),
                    onPictureSelected = onPictureSelected,
                )
                2 -> TwoImagesTweetLayout(
                    images = images,
                    onPictureSelected = onPictureSelected,
                )
                3 -> ThreeImagesTweetLayout(
                    images = images,
                    onPictureSelected = onPictureSelected,
                )
                else -> FourImagesTweetLayout(
                    images = images,
                    onPictureSelected = onPictureSelected,
                )
            }
        }
    }
}

@Composable
fun OneImageTweetLayout(
    image: ImageUI,
    onPictureSelected: (Long) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomStart,
    ) {
        val context = LocalContext.current
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            TweetImage(
                image = image,
                contentDescription = stringResource(id = R.string.tweet_image),
                imageLoader = if (image.imageType == Video) {
                    remember {
                        ImageLoader.Builder(context)
                            .components {
                                add(VideoFrameDecoder.Factory())
                            }.build()
                    }
                } else Coil.imageLoader(
                    LocalContext.current
                ),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
                onPictureSelected = onPictureSelected,
            )
            if (isGifImage(image)) {
                Image(
                    painter = painterResource(id = R.drawable.img_play),
                    contentDescription = null,
                )
            }
        }
        if (isGifImage(image) || isVideoImage(image)) {
            if (isGifImage(image)) {
                ImageLabel(
                    text = "GIF",
                    contentDescription = stringResource(id = R.string.gif_image)
                )
            } else if (isVideoImage(image)) {
                ImageLabel(
                    text = image.time ?: "",
                    contentDescription = stringResource(id = R.string.video_image)
                )
            }
        }
    }
}

@Composable
private fun ImageLabel(text: String, contentDescription: String) {
    Text(
        text = text,
        color = TwitterMirroringTheme.extraColors.white,
        style = TwitterMirroringTheme.typography.h6,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .semantics { this.contentDescription = contentDescription }
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(
                color = TwitterMirroringTheme.extraColors.black.copy(
                    alpha = 0.7f
                )
            )
            .padding(horizontal = 4.dp, vertical = 2.dp)
    )
}

private fun isGifImage(image: ImageUI) =
    image.imageType == Gif

private fun isVideoImage(image: ImageUI) =
    image.imageType == Video

@Composable
fun TwoImagesTweetLayout(
    images: List<ImageUI>,
    onPictureSelected: (Long) -> Unit,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        OneImageColumn(
            images = images.take(1),
            modifier = Modifier.weight(1f),
            onPictureSelected = onPictureSelected
        )
        Spacer(Modifier.width(IMAGE_SEPARATION_DP.dp))
        OneImageColumn(
            images = images.drop(1).take(1),
            modifier = Modifier.weight(1f),
            onPictureSelected = onPictureSelected,
        )
    }
}

@Composable
fun ThreeImagesTweetLayout(
    images: List<ImageUI>,
    onPictureSelected: (Long) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        OneImageColumn(
            images = images.take(1),
            modifier = Modifier.weight(1f),
            onPictureSelected = onPictureSelected
        )
        Spacer(Modifier.width(IMAGE_SEPARATION_DP.dp))
        TwoImagesColumn(
            images = images.drop(1).take(2),
            modifier = Modifier.weight(1f),
            onPictureSelected = onPictureSelected
        )
    }
}

@Composable
fun FourImagesTweetLayout(
    images: List<ImageUI>,
    onPictureSelected: (Long) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TwoImagesColumn(
            images = images.take(2),
            modifier = Modifier.weight(1f),
            onPictureSelected = onPictureSelected,
        )
        Spacer(Modifier.width(IMAGE_SEPARATION_DP.dp))
        TwoImagesColumn(
            images = images.drop(2).take(2),
            modifier = Modifier.weight(1f),
            onPictureSelected = onPictureSelected,
        )
    }
}

@Composable
private fun OneImageColumn(
    images: List<ImageUI>,
    modifier: Modifier = Modifier,
    onPictureSelected: (Long) -> Unit,
) {
    val imageContentDescription = stringResource(id = R.string.tweet_image)
    TweetImage(
        image = images[0],
        contentDescription = imageContentDescription,
        contentScale = ContentScale.Crop,
        onPictureSelected = onPictureSelected,
        modifier = modifier
            .aspectRatio(MULTIPLE_PICS_COLUMN_ASPECT_RATIO),
    )
}

@Composable
private fun TwoImagesColumn(
    images: List<ImageUI>,
    modifier: Modifier = Modifier,
    onPictureSelected: (Long) -> Unit,
) {
    val imageContentDescription = stringResource(id = R.string.tweet_image)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(MULTIPLE_PICS_COLUMN_ASPECT_RATIO),
    ) {
        TweetImage(
            image = images[0],
            contentDescription = imageContentDescription,
            contentScale = ContentScale.Crop,
            onPictureSelected = onPictureSelected,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
        )
        Spacer(Modifier.height(IMAGE_SEPARATION_DP.dp))
        TweetImage(
            image = images[1],
            contentDescription = imageContentDescription,
            contentScale = ContentScale.Crop,
            onPictureSelected = onPictureSelected,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
        )
    }
}

