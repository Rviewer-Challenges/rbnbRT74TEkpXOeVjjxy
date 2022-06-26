package com.rumosoft.library_components.components

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test

internal class TweetStatsKtTest : ScreenshotTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tweetActionButtons_comparesScreenshot() {
        val stats = listOf(
            "1" to "stat1",
            "2" to "stat2",
            "3" to "stat3",
        )

        composeTestRule.setContent {
            Column {
                TweetStats(stats)
            }
        }

        compareScreenshot(composeTestRule)
    }
}