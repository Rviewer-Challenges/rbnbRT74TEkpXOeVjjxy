package com.rumosoft.library_components.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
fun TweetName(
    username: String,
    showTick: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(end = TwitterMirroringTheme.paddings.tinyPadding)
    ) {
        val headerText = getAnnotatedHeaderString(username, showTick)
        val inlineContentMap = mapOf(
            TICK_IMAGE_ID to InlineTextContent(
                Placeholder(20.sp, 20.sp, PlaceholderVerticalAlign.TextCenter)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_tick),
                    modifier = Modifier.fillMaxHeight(),
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

@Composable
internal fun getAnnotatedHeaderString(
    username: String,
    showTick: Boolean,
) = buildAnnotatedString {
    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
        append(username)
    }
    if (showTick) {
        append(" ")
        appendInlineContent(id = TICK_IMAGE_ID)
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TweetNamePreview() {
    val sampleTweet = remember { SampleTweetData.sampleTweet() }
    TwitterMirroringTheme {
        TweetName(
            username = sampleTweet.username,
            showTick = true,
        )
    }
}
