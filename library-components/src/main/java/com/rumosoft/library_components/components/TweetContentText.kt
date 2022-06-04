package com.rumosoft.library_components.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rumosoft.library_components.components.sampledata.SampleTweetData
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

const val MAX_TWEET_LENGTH = 280

@Composable
fun TweetContentText(
    message: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = if (message.length > MAX_TWEET_LENGTH) message.substring(0, MAX_TWEET_LENGTH) else message,
        style = TwitterMirroringTheme.typography.body1,
        modifier = modifier
            .padding(top = 2.dp, end = TwitterMirroringTheme.paddings.medium),
    )
}

@Preview(
    showBackground = true
)
@Composable
fun TweetContentTextPreview() {
    val sampleTweet = remember { SampleTweetData.sampleTweet() }
    TwitterMirroringTheme {
        TweetContentText(sampleTweet.message)
    }
}
