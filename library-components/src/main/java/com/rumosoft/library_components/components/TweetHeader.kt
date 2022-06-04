package com.rumosoft.library_components.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.rumosoft.library_components.R
import com.rumosoft.library_components.components.sampledata.SampleTweetData
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

private const val TICK_IMAGE_ID = "tickImageId"

@Composable
fun TweetHeader(
    username: String,
    nickname: String,
    elapsedTime: String,
    showTick: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = TwitterMirroringTheme.paddings.medium)
    ) {
        val headerText = getAnnotatedHeaderString(username, showTick, nickname, elapsedTime)
        val inlineContentMap = mapOf(
            TICK_IMAGE_ID to InlineTextContent(
                Placeholder(20.sp, 20.sp, PlaceholderVerticalAlign.TextCenter)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_tick),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = ""
                )
            }
        )
        Text(
            text = headerText,
            inlineContent = inlineContentMap,
            style = TwitterMirroringTheme.typography.body1,
        )
    }
}

internal fun getAnnotatedHeaderString(
    username: String,
    showTick: Boolean,
    nickname: String,
    elapsedTime: String
) = buildAnnotatedString {
    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
        append(username)
    }
    if (showTick) {
        append(" ")
        appendInlineContent(id = TICK_IMAGE_ID)
    }
    append(" @$nickname ·$elapsedTime")
}

@Preview(
    showBackground = true
)
@Composable
fun TweetHeaderPreview() {
    val sampleTweet = remember { SampleTweetData.sampleTweet() }
    TwitterMirroringTheme {
        TweetHeader(
            username = sampleTweet.username,
            nickname = sampleTweet.nickname,
            elapsedTime = sampleTweet.elapsedTime,
            showTick = true,
        )
    }
}
