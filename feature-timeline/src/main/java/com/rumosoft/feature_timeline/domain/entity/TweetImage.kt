package com.rumosoft.feature_timeline.domain.entity

import com.rumosoft.feature_timeline.domain.entity.ImageType.Static

data class TweetImage(
    val id: Long,
    val url: String,
    val time: String? = null,
    val imageType: ImageType = Static
)

enum class ImageType {
    Static, Gif, Video
}