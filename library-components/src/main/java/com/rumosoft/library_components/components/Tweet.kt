package com.rumosoft.library_components.components

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
import com.rumosoft.library_components.components.model.TweetActionsClick
import com.rumosoft.library_components.components.sampledata.SampleTweetData.sampleTweet
import com.rumosoft.library_components.infrastructure.openUrl
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun Tweet(
    profileImageUrl: String,
    username: String,
    nickname: String,
    message: String,
    elapsedTime: String,
    numComments: String,
    numRetweets: String,
    numLikes: String,
    verified: Boolean = false,
    images: List<String> = emptyList(),
    onActionsClick: TweetActionsClick = object : TweetActionsClick {
        override fun onCommentsClick() {}
        override fun onRetweetsClick() {}
        override fun onLikesClick() {}
        override fun onShareClick() {}
    },
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = TwitterMirroringTheme.paddings.medium)
            .padding(top = TwitterMirroringTheme.paddings.medium)
    ) {
        ProfileImage(
            profileImageUrl = profileImageUrl,
            profileName = username,
        )
        Column {
            TweetHeader(
                username = username,
                nickname = nickname,
                elapsedTime = elapsedTime,
                showTick = verified,
                modifier = Modifier.padding(start = TwitterMirroringTheme.paddings.medium)
            )
            TweetContent(
                message = message,
                modifier = Modifier.padding(start = TwitterMirroringTheme.paddings.medium),
                images = images,
                onUrlClick = { url ->
                    context.openUrl(url)
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
}

@Preview(
    showBackground = true,
    widthDp = 360,
)
@Composable
fun TweetUnverifiedUserPreview() {
    val sampleTweet = remember { sampleTweet() }
    TwitterMirroringTheme {
        Tweet(
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
fun TweetVerifiedUserPreview() {
    val sampleTweet = remember { sampleTweet() }
    TwitterMirroringTheme {
        Tweet(
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