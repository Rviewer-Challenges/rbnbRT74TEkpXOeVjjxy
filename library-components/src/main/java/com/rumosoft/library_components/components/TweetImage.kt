package com.rumosoft.library_components.components

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.Coil
import coil.ImageLoader
import coil.compose.AsyncImage
import com.rumosoft.library_components.R
import com.rumosoft.library_components.components.model.ImageUI

@Composable
fun TweetImage(
    image: ImageUI,
    contentDescription: String,
    contentScale: ContentScale,
    modifier: Modifier = Modifier,
    onPictureSelected: (Long) -> Unit = {},
    imageLoader: ImageLoader = Coil.imageLoader(LocalContext.current),
) {
    AsyncImage(
        model = image.url,
        error = painterResource(id = R.drawable.img_error),
        imageLoader = imageLoader,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
            .clickable { onPictureSelected(image.id) },
    )
}