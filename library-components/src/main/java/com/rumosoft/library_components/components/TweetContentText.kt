package com.rumosoft.library_components.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import com.rumosoft.library_components.components.sampledata.SampleTweetData
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme


private const val MAX_TWEET_LENGTH = 280
private const val MENTIONS_REGEX =
    "(?<=^|(?<=[^a-zA-Z0-9-\\.]))@[A-Za-z0-9-\\_]+"
private const val URL_REGEX =
    "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})"
private const val URL_TAG = "urlTag"

@Composable
fun TweetContentText(
    message: String,
    modifier: Modifier = Modifier,
    onUrlClick: (url: String) -> Unit = {}
) {
    val bodyString = getAnnotatedBodyString(message = message)
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
            bodyString.getStringAnnotations(
                tag = URL_TAG, start = offset,
                end = offset
            ).firstOrNull()?.let { annotation ->
                onUrlClick(annotation.item)
            }
        },
    )
}

@Composable
internal fun getAnnotatedBodyString(
    message: String
) = buildAnnotatedString {
    val substring =
        if (message.length > MAX_TWEET_LENGTH) message.substring(0, MAX_TWEET_LENGTH) else message
    val toHighlightRegex =
        "$MENTIONS_REGEX|$URL_REGEX".toRegex()
    val toHighlight = getElementsToHighlight(toHighlightRegex, substring)
    val notHighlights = getNotHighlightedElements(substring, toHighlightRegex)

    if (toHighlight.isNotEmpty()) {
        notHighlights.zip(toHighlight + "") { message, highlight ->
            append(message)
            addHighlightedChunk(highlight)
        }
    } else {
        append(substring)
    }
}

@Composable
private fun AnnotatedString.Builder.addHighlightedChunk(highlight: String) {
    if (highlight.isNotEmpty()) {
        if (isUrl(highlight)) {
            pushStringAnnotation(
                tag = URL_TAG,
                annotation = highlight
            )
            highlightText(highlight)
            pop()
        } else {
            highlightText(highlight)
        }
    }
}

@Composable
private fun getNotHighlightedElements(
    substring: String,
    toHighlightRegex: Regex
) = substring.split(toHighlightRegex)

@Composable
private fun getElementsToHighlight(
    toHighlightRegex: Regex,
    substring: String
) = toHighlightRegex.findAll(substring).map { it.value }.toList()

@Composable
private fun AnnotatedString.Builder.highlightText(highlight: String) {
    withStyle(style = SpanStyle(TwitterMirroringTheme.extraColors.blue)) {
        append(highlight)
    }
}

@Composable
private fun isUrl(highlight: String) = URL_REGEX.toRegex().matches(highlight)

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
