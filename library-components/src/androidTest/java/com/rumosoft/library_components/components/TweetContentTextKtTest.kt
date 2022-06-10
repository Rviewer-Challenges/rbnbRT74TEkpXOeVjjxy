package com.rumosoft.library_components.components

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rumosoft.library_components.R
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

internal class TweetContentTextKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tweetContentText_showsText() {
        val message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit"

        composeTestRule.setContent {
            TweetContentText(message = message)
        }

        composeTestRule.onNodeWithText(message)
            .assertIsDisplayed()
    }

    @Test
    fun tweetContentText_doesntShowTooLongText() {
        val message =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

        composeTestRule.setContent {
            TweetContentText(message = message)
        }

        composeTestRule.onNodeWithText(message)
            .assertDoesNotExist()
    }

    @Test
    fun tweetClickUrl_invokesOpenUrlListener() {
        val url = "https://www.google.es"
        val onUrlClick: (String) -> Unit = mockk<(String) -> Unit>().also {
            justRun { it(url) }
        }

        composeTestRule.setContent {
            TweetContentText(
                message = url,
                onUrlClick = onUrlClick
            )
        }

        composeTestRule.onNodeWithContentDescription("Tweet text").performClick()

        verify { onUrlClick(url) }
    }

    @Test
    fun tweetClickMention_doesNotInvokeOpenUrlListener() {
        val mention = "@whatever"
        val onUrlClick: (String) -> Unit = mockk()
        lateinit var contentDescription: String

        composeTestRule.setContent {
            contentDescription = stringResource(id = R.string.tweet_text)
            TweetContentText(
                message = mention,
                onUrlClick = onUrlClick
            )
        }

        composeTestRule.onNodeWithContentDescription(contentDescription).performClick()

        verify(exactly = 0) { onUrlClick(any()) }
    }
}