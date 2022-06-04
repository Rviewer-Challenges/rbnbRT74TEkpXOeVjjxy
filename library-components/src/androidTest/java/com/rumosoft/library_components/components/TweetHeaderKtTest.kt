package com.rumosoft.library_components.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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
        val headerString = getAnnotatedHeaderString(
            username = username,
            showTick = showTick,
            nickname = nickname,
            elapsedTime = elapsedTime
        )

        composeTestRule.setContent {
            TweetHeader(
                username = username,
                nickname = nickname,
                elapsedTime = elapsedTime,
                showTick = showTick
            )
        }

        Thread.sleep(5_000)

        composeTestRule.onNodeWithText(headerString.toString())
            .assertIsDisplayed()
    }
}