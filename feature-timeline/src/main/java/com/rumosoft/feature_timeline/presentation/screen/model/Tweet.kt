package com.rumosoft.feature_timeline.presentation.screen.model

import com.rumosoft.feature_timeline.domain.entity.ImageType
import com.rumosoft.feature_timeline.domain.entity.Tweet
import com.rumosoft.feature_timeline.domain.entity.TweetImage
import com.rumosoft.library_components.components.model.ImageTypeUI
import com.rumosoft.library_components.components.model.ImageUI
import kotlin.math.ln
import kotlin.math.pow

data class TweetUI(
    val id: Long,
    val username: String,
    val nickname: String,
    val profileImageUrl: String,
    val message: String,
    val numComments: String,
    val numRetweets: String,
    val numLikes: String,
    val elapsedTime: String,
    val verified: Boolean,
    val images: List<ImageUI>,
)

fun Tweet.toTweetUI(): TweetUI {
    fun getFormattedNumber(count: Long): String {
        if (count <= 0) return ""
        if (count < 1000) return count.toString()
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
    }

    return TweetUI(
        id = id,
        username = username,
        nickname = nickname,
        profileImageUrl = profileImageUrl,
        message = message,
        numComments = getFormattedNumber(numComments),
        numRetweets = getFormattedNumber(numRetweets),
        numLikes = getFormattedNumber(numLikes),
        elapsedTime = elapsedTime,
        verified = verified,
        images = images.map { it.toImageUI() },
    )
}

fun TweetImage.toImageUI(): ImageUI {
    return ImageUI(
        id = id,
        url = url,
        imageType = imageType.toScreenImageType()
    )
}

fun ImageType.toScreenImageType(): ImageTypeUI {
    return when (this) {
        ImageType.Static -> ImageTypeUI.Static
        ImageType.Gif -> ImageTypeUI.Gif
    }
}