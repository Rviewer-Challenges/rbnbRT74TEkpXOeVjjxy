package com.rumosoft.library_components.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
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
    zoomable: Boolean = false,
    onPictureSelected: ((Long) -> Unit)? = null,
    imageLoader: ImageLoader = Coil.imageLoader(LocalContext.current),
) {
    var scale by remember { mutableStateOf(1f) }
    AsyncImage(
        model = image.url,
        error = painterResource(id = R.drawable.img_error),
        imageLoader = imageLoader,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
            .then(
                if (onPictureSelected != null) {
                    Modifier.clickable { onPictureSelected(image.id) }
                } else Modifier
            )
            .then(
                if (zoomable) {
                    Modifier
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale
                        )
                        .pointerInput(Unit) {
                            detectTransformGestures { _, _, zoom, _ ->
                                scale = when {
                                    scale < 1f -> 1f
                                    scale > 3f -> 3f
                                    else -> scale * zoom
                                }
                            }
                        }
                } else Modifier
            ),
    )
}