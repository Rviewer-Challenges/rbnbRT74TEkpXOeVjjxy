package com.rumosoft.library_components.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rumosoft.library_components.R
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

private const val IMAGE_SEPARATION_DP = 4
private const val MULTIPLE_PICS_COLUMN_ASPECT_RATIO = 0.9f

@Composable
fun TweetContentImages(images: List<String>) {
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
                1 -> OneImageTweetLayout(images.first())
                2 -> TwoImagesTweetLayout(images)
                3 -> ThreeImagesTweetLayout(images)
                else -> FourImagesTweetLayout(images)
            }
        }
    }
}

@Composable
fun OneImageTweetLayout(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        error = painterResource(id = R.drawable.twitter_logo),
        contentDescription = stringResource(id = R.string.tweet_image),
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth(),
    )
}

@Composable
fun TwoImagesTweetLayout(images: List<String>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        OneImageColumn(
            images = images.take(1),
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.width(IMAGE_SEPARATION_DP.dp))
        OneImageColumn(
            images = images.drop(1).take(1),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ThreeImagesTweetLayout(images: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        OneImageColumn(
            images = images.take(1),
            modifier = Modifier.weight(1f),
        )
        Spacer(Modifier.width(IMAGE_SEPARATION_DP.dp))
        TwoImagesColumn(
            images = images.drop(1).take(2),
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun FourImagesTweetLayout(images: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TwoImagesColumn(
            images = images.take(2),
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.width(IMAGE_SEPARATION_DP.dp))
        TwoImagesColumn(
            images = images.drop(2).take(2),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun OneImageColumn(
    images: List<String>,
    modifier: Modifier = Modifier,
) {
    val imageContentDescription = stringResource(id = R.string.tweet_image)
    AsyncImage(
        model = images[0],
        error = painterResource(id = R.drawable.twitter_logo),
        contentDescription = imageContentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .aspectRatio(MULTIPLE_PICS_COLUMN_ASPECT_RATIO),
    )
}

@Composable
private fun TwoImagesColumn(
    images: List<String>,
    modifier: Modifier = Modifier,
) {
    val imageContentDescription = stringResource(id = R.string.tweet_image)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(MULTIPLE_PICS_COLUMN_ASPECT_RATIO),
    ) {
        AsyncImage(
            model = images[0],
            error = painterResource(id = R.drawable.twitter_logo),
            contentDescription = imageContentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
        )
        Spacer(Modifier.height(IMAGE_SEPARATION_DP.dp))
        AsyncImage(
            model = images[1],
            error = painterResource(id = R.drawable.twitter_logo),
            contentDescription = imageContentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
        )
    }
}
