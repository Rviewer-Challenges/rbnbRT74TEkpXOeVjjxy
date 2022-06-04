package com.rumosoft.library_components.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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
        val message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

        composeTestRule.setContent {
            TweetContentText(message = message)
        }

        composeTestRule.onNodeWithText(message)
            .assertDoesNotExist()
    }
}