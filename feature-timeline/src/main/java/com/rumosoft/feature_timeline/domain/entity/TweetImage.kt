package com.rumosoft.feature_timeline.domain.entity

import com.rumosoft.feature_timeline.domain.entity.ImageType.Static

data class TweetImage(
    val url: String,
    val imageType: ImageType = Static
)

enum class ImageType {
    Static, Gif
}