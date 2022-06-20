package com.rumosoft.library_components.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rumosoft.library_components.components.model.ImageUI
import com.rumosoft.library_components.components.model.TweetActionsClick
import com.rumosoft.library_components.components.sampledata.SampleTweetData.sampleTweet
import com.rumosoft.library_components.infrastructure.openUrl
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun TweetFullWidth(
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
    Column {
        Row(
            modifier = Modifier
                .padding(
                    start = TwitterMirroringTheme.paddings.medium,
                    top = TwitterMirroringTheme.paddings.medium
                )
        ) {
            ProfileImage(
                profileImageUrl = profileImageUrl,
                profileName = username,
            )
            Column(
                modifier = Modifier
                    .padding(start = TwitterMirroringTheme.paddings.medium)
                    .height(55.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                TweetName(
                    username = username,
                    showTick = verified,
                )
                Text(
                    text = "@$nickname",
                    color = TwitterMirroringTheme.colors.onBackground,
                    style = TwitterMirroringTheme.typography.body1
                )
            }
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
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
)
@Composable
fun TweetFullWidthUnverifiedUserPreview() {
    val sampleTweet = remember { sampleTweet() }
    TwitterMirroringTheme {
        TweetFullWidth(
            tweetId = 1,
            profileImageUrl = sampleTweet.profileImageUrl,
            username = sampleTweet.username,
            nickname = sampleTweet.nickname,
            message = sampleTweet.message,
            numComments = sampleTweet.numComments,
            numRetweets = sampleTweet.numRetweets,
            numLikes = sampleTweet.numLikes,
            elapsedTime = sampleTweet.elapsedTime,
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
)
@Composable
fun TweetFullWidthVerifiedUserPreview() {
    val sampleTweet = remember { sampleTweet() }
    TwitterMirroringTheme {
        TweetFullWidth(
            tweetId = 1,
            profileImageUrl = sampleTweet.profileImageUrl,
            username = sampleTweet.username,
            nickname = sampleTweet.nickname,
            message = sampleTweet.message,
            elapsedTime = sampleTweet.elapsedTime,
            numComments = sampleTweet.numComments,
            numRetweets = sampleTweet.numRetweets,
            numLikes = sampleTweet.numLikes,
            verified = true,
        )
    }
}