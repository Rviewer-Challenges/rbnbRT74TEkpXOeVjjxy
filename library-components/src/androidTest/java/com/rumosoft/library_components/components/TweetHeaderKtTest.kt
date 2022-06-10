package com.rumosoft.library_components.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.AnnotatedString
import org.junit.Rule
import org.junit.Test

internal class TweetHeaderKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tweetHeader_showsElements() {
        val username = "username"
        val nickname = "nickname"
        val elapsedTime = "1h"
        val showTick = true
        lateinit var headerString: AnnotatedString

        composeTestRule.setContent {
            headerString = getAnnotatedHeaderString(
                username = username,
                showTick = showTick,
                nickname = nickname,
                elapsedTime = elapsedTime
            )
            TweetHeader(
                username = username,
                nickname = nickname,
                elapsedTime = elapsedTime,
                showTick = showTick
            )
        }

        composeTestRule.onNodeWithText(headerString.toString())
            .assertIsDisplayed()
    }
}