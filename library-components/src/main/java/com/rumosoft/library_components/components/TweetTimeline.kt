package com.rumosoft.library_components.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.rumosoft.library_components.components.model.ImageUI
import com.rumosoft.library_components.components.model.TweetActionsClick
import com.rumosoft.library_components.components.sampledata.SampleTweetData.sampleTweet
import com.rumosoft.library_components.infrastructure.openUrl
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun TweetTimeline(
    tweetId: Long,
    profileImageUrl: String,
    username: String,
    nickname: String,
    message: String,
    elapsedTime: String,
    numComments: String,
    numRetweets: String,
    numLikes: String,
    verified: Boolean = false,
    images: List<ImageUI> = emptyList(),
    onActionsClick: TweetActionsClick = object : TweetActionsClick {
        override fun onCommentsClick() {}
        override fun onRetweetsClick() {}
        override fun onLikesClick() {}
        override fun onShareClick() {}
    },
    onTweetSelected: (Long) -> Unit = { _ -> },
    onPictureSelected: (Long, Long) -> Unit = { _, _ -> },
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTweetSelected(tweetId) }
            .padding(
                start = TwitterMirroringTheme.paddings.medium,
                top = TwitterMirroringTheme.paddings.medium
            )
    ) {
        ProfileImage(
            profileImageUrl = profileImageUrl,
            profileName = username,
        )
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TweetName(
                    username = username,
                    showTick = verified,
                    modifier = Modifier.padding(start = TwitterMirroringTheme.paddings.medium)
                )
                TweetStackedHeader(message = "@$nickname · $elapsedTime")
            }
            TweetContent(
                tweetId = tweetId,
                message = message,
                modifier = Modifier.padding(start = TwitterMirroringTheme.paddings.medium),
                images = images,
                onTweetSelected = onTweetSelected,
                onHighlightedTextClick = { text, tag ->
                    when (tag) {
                        URL_TAG -> context.openUrl(text)
                        else -> {
                            /* Do nothing */
                        }
                    }
                },
                onPictureSelected = { imageId ->
                    onPictureSelected(tweetId, imageId)
                },
            )
            TweetActionButtons(
                numComments = numComments,
                numRetweets = numRetweets,
                numLikes = numLikes,
                onActionsClick = onActionsClick,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
)
@Composable
fun TweetTimelineUnverifiedUserPreview() {
    val sampleTweet = remember { sampleTweet() }
    TwitterMirroringTheme {
        TweetTimeline(
            tweetId = 1,
            profileImageUrl = sampleTweet.profileImageUrl,
            username = sampleTweet.username,
            nickname = sampleTweet.nickname,
            message = sampleTweet.message,
            numComments = sampleTweet.numComments,
            numRetweets = sampleTweet.numRetweets,
            numLikes = sampleTweet.numLikes,
            elapsedTime = sampleTweet.shortElapsedTime,
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
)
@Composable
fun TweetTimelineVerifiedUserPreview() {
    val sampleTweet = remember { sampleTweet() }
    TwitterMirroringTheme {
        TweetTimeline(
            tweetId = 1,
            profileImageUrl = sampleTweet.profileImageUrl,
            username = sampleTweet.username,
            nickname = sampleTweet.nickname,
            message = sampleTweet.message,
            elapsedTime = sampleTweet.shortElapsedTime,
            numComments = sampleTweet.numComments,
            numRetweets = sampleTweet.numRetweets,
            numLikes = sampleTweet.numLikes,
            verified = true,
        )
    }
}