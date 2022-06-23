package com.rumosoft.feature_timeline.presentation.screen.model

import com.rumosoft.feature_timeline.domain.entity.ImageType
import com.rumosoft.feature_timeline.domain.entity.Tweet
import com.rumosoft.feature_timeline.domain.entity.TweetImage
import com.rumosoft.feature_timeline.infrastructure.extensions.formatDuration
import com.rumosoft.feature_timeline.presentation.screen.model.TweetUI.Companion.longFormatter
import com.rumosoft.feature_timeline.presentation.screen.model.TweetUI.Companion.shortFormatter
import com.rumosoft.library_components.components.model.ImageTypeUI
import com.rumosoft.library_components.components.model.ImageUI
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.ln
import kotlin.math.pow
import kotlin.time.Duration.Companion.parseIsoStringOrNull


data class TweetUI(
    val id: Long,
    val username: String,
    val nickname: String,
    val profileImageUrl: String,
    val message: String,
    val numComments: String,
    val numRetweets: String,
    val numLikes: String,
    val shortElapsedTime: String,
    val longElapsedTime: String,
    val verified: Boolean,
    val images: List<ImageUI>,
) {
    companion object {
        val shortFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM")
        val longFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm · dd MMM yy")
    }
}

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
        shortElapsedTime = posted.formatToLocalDate(shortFormatter),
        longElapsedTime = posted.formatToLocalDate(longFormatter),
        verified = verified,
        images = images.map { it.toImageUI() },
    )
}

fun Instant.formatToLocalDate(longFormatter: DateTimeFormatter?): String =
    LocalDateTime
        .ofInstant(this, ZoneId.systemDefault())
        .format(longFormatter)

fun TweetImage.toImageUI(): ImageUI {
    return ImageUI(
        id = id,
        url = url,
        time = time?.let { parseIsoStringOrNull(it)?.formatDuration() },
        imageType = imageType.toScreenImageType()
    )
}

fun ImageType.toScreenImageType(): ImageTypeUI {
    return when (this) {
        ImageType.Static -> ImageTypeUI.Static
        ImageType.Gif -> ImageTypeUI.Gif
        ImageType.Video -> ImageTypeUI.Video
    }
}
