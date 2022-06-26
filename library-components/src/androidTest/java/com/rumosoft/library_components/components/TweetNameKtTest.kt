package com.rumosoft.library_components.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.AnnotatedString
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test

internal class TweetNameKtTest: ScreenshotTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tweetName_showsElements() {
        val username = "username"
        val showTick = true
        lateinit var headerString: AnnotatedString

        composeTestRule.setContent {
            headerString = getAnnotatedHeaderString(
                username = username,
                showTick = showTick,
            )
            TweetName(
                username = username,
                showTick = showTick
            )
        }

        composeTestRule.onNodeWithText(headerString.toString())
            .assertIsDisplayed()
        compareScreenshot(composeTestRule)
    }
}