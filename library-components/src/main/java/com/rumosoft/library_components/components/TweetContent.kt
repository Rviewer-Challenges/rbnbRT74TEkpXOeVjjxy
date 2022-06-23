package com.rumosoft.library_components.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rumosoft.library_components.R
import com.rumosoft.library_components.components.model.ImageUI
import com.rumosoft.library_components.components.sampledata.SampleTweetData
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme


private const val MAX_TWEET_LENGTH = 280
private const val MENTIONS_REGEX =
    "(?<=^|(?<=[^a-zA-Z\\d-\\.]))@[a-zA-Z\\d-\\_]+"
private const val HASHTAGS_REGEX =
    "(?<=^|(?<=[^a-zA-Z\\d-\\.]))#[a-zA-Z\\d-\\_]+"
private const val URL_REGEX =
    "(https?:\\//(?:www\\.|(?!www))[a-zA-Z\\d][a-zA-Z\\d-]+[a-zA-Z\\d]\\.\\S{2,}|www\\.[a-zA-Z\\d][a-zA-Z\\d-]+[a-zA-Z\\d]\\.\\S{2,}|https?:\\//(?:www\\.|(?!www))[a-zA-Z\\d]+\\.\\S{2,}|www\\.[a-zA-Z\\d]+\\.\\S{2,})"
internal const val URL_TAG = "urlTag"
private const val MENTION_TAG = "mentionTag"
private const val HASHTAG_TAG = "hashtagTag"

@Composable
fun TweetContent(
    message: String,
    images: List<ImageUI>,
    modifier: Modifier = Modifier,
    tweetId: Long = 1L,
    onHighlightedTextClick: (String, String) -> Unit = { _, _ -> },
    onTweetSelected: (Long) -> Unit = { _ -> },
    onPictureSelected: (Long) -> Unit = {},
) {
    Column {
        TweetContentText(
            tweetId = tweetId,
            message = message,
            modifier = modifier,
            onTweetSelected = onTweetSelected,
            onHighlightedTextClick = onHighlightedTextClick
        )
        TweetContentImages(
            images = images,
            onPictureSelected = onPictureSelected,
        )
    }
}

@Composable
private fun TweetContentText(
    tweetId: Long,
    message: String,
    modifier: Modifier = Modifier,
    onTweetSelected: (Long) -> Unit = { _ -> },
    onHighlightedTextClick: (String, String) -> Unit,
) {
    val highlightColor = TwitterMirroringTheme.extraColors.blue
    val bodyString = remember { getAnnotatedBodyString(message = message, highlightColor = highlightColor) }
    val textTweetContentDescription = stringResource(R.string.tweet_text)

    ClickableText(
        text = bodyString,
        style = TwitterMirroringTheme.typography.body1,
        modifier = modifier
            .padding(top = 2.dp, end = TwitterMirroringTheme.paddings.medium)
            .semantics {
                contentDescription = textTweetContentDescription
            },
        onClick = { offset ->
            var consumed = false
            val highlightedTags = listOf(URL_TAG, MENTION_TAG, HASHTAG_TAG)
            highlightedTags.forEach { tag ->
                bodyString.getStringAnnotations(
                    tag = tag, start = offset,
                    end = offset
                ).firstOrNull()?.let { annotation ->
                    onHighlightedTextClick(annotation.item, tag)
                    consumed = true
                }
            }
            if (!consumed)
                onTweetSelected(tweetId)
        },
    )
}


internal fun getAnnotatedBodyString(
    message: String,
    highlightColor: Color,
): AnnotatedString = buildAnnotatedString {
    val substring =
        if (message.length > MAX_TWEET_LENGTH) message.substring(0, MAX_TWEET_LENGTH) else message
    val toHighlightRegex =
        "$MENTIONS_REGEX|$HASHTAGS_REGEX|$URL_REGEX".toRegex()
    val toHighlight = getElementsToHighlight(toHighlightRegex, substring)
    val notHighlights = getNotHighlightedElements(substring, toHighlightRegex)

    if (toHighlight.isNotEmpty()) {
        notHighlights.zip(toHighlight + "") { message, highlight ->
            append(message)
            addHighlightedChunk(highlight, highlightColor)
        }
    } else {
        append(substring)
    }
}

private fun getElementsToHighlight(
    toHighlightRegex: Regex,
    substring: String,
) = toHighlightRegex.findAll(substring).map { it.value }.toList()

private fun AnnotatedString.Builder.highlightText(
    highlight: String, highlightColor: Color
) {
    withStyle(style = SpanStyle(highlightColor)) {
        append(highlight)
    }
}

private fun getNotHighlightedElements(
    substring: String,
    toHighlightRegex: Regex
) = substring.split(toHighlightRegex)

private fun AnnotatedString.Builder.addHighlightedChunk(
    highlight: String,
    highlightColor: Color,
) {
    if (highlight.isNotEmpty()) {
        when {
            isUrl(highlight) -> {
                highlight(
                    text = highlight,
                    tag = URL_TAG,
                    textTransformation = ::stripWebPrefix,
                    highlightColor = highlightColor,
                )
            }
            isMention(highlight) -> {
                highlight(
                    text = highlight,
                    tag = MENTION_TAG,
                    highlightColor = highlightColor,
                )
            }
            else -> {
                highlight(
                    text = highlight,
                    tag = HASHTAG_TAG,
                    highlightColor = highlightColor,
                )
            }
        }
    }
}

private fun AnnotatedString.Builder.highlight(
    text: String,
    tag: String,
    textTransformation: (String) -> String = { it },
    highlightColor: Color,
) {
    pushStringAnnotation(
        tag = tag,
        annotation = text
    )
    highlightText(textTransformation(text), highlightColor)
    pop()
}

private fun isUrl(highlight: String) = URL_REGEX.toRegex().matches(highlight)

private fun isMention(highlight: String) = MENTIONS_REGEX.toRegex().matches(highlight)

private fun stripWebPrefix(highlight: String) =
    highlight.replace("http[s]?://".toRegex(), "")

@Preview(
    showBackground = true
)
@Composable
fun TweetContentTextPreview() {
    val sampleTweet = remember { SampleTweetData.sampleTweet() }
    TwitterMirroringTheme {
        TweetContent(
            message = sampleTweet.message,
            images = sampleTweet.images
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TweetContentWithMentionsPreview() {
    val sampleTweet = remember {
        SampleTweetData.sampleTweet().copy(message = "text with @mentions and more text")
    }
    TwitterMirroringTheme {
        TweetContent(
            message = sampleTweet.message,
            images = sampleTweet.images
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TweetContentWithUrlsPreview() {
    val sampleTweet = remember {
        SampleTweetData.sampleTweet()
            .copy(message = "text with http://url.com and more https://web.urls.com")
    }
    TwitterMirroringTheme {
        TweetContent(
            message = sampleTweet.message,
            images = sampleTweet.images
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TweetContentWithHashtagsPreview() {
    val sampleTweet = remember {
        SampleTweetData.sampleTweet()
            .copy(message = "text with #hastag and more #hastags and #otherhashtag")
    }
    TwitterMirroringTheme {
        TweetContent(
            message = sampleTweet.message,
            images = sampleTweet.images
        )
    }
}
