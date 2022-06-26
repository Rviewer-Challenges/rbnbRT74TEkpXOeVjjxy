package com.rumosoft.library_components.components

import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import com.rumosoft.library_components.components.sampledata.SampleTweetData
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme
import org.junit.Rule
import org.junit.Test

internal class TweetFullWidthTest : ScreenshotTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tweetFullWidth_whenUnverifiedUser_comparesScreenshot() {
        composeTestRule.setContent {
            val sampleTweet = remember { SampleTweetData.sampleTweet() }
            TwitterMirroringTheme {
                TweetFullWidth(
                    tweetId = 1,
                    profileImageUrl = sampleTweet.profileImageUrl,
                    username = sampleTweet.username,
                    nickname = sampleTweet.nickname,
                    message = sampleTweet.message,
                    numComments = sampleTweet.numComments,
                    numRetweets = sampleTweet.numRetweets,
                    numQuoteTweets = sampleTweet.numQuoteTweets,
                    numLikes = sampleTweet.numLikes,
                    elapsedTime = sampleTweet.longElapsedTime,
                )
            }
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun tweetFullWidth_whenVerifiedUser_comparesScreenshot() {
        composeTestRule.setContent {
            val sampleTweet = remember { SampleTweetData.sampleTweet() }
            TwitterMirroringTheme {
                TweetFullWidth(
                    tweetId = 1,
                    profileImageUrl = sampleTweet.profileImageUrl,
                    username = sampleTweet.username,
                    nickname = sampleTweet.nickname,
                    message = sampleTweet.message,
                    numComments = sampleTweet.numComments,
                    numRetweets = sampleTweet.numRetweets,
                    numQuoteTweets = sampleTweet.numQuoteTweets,
                    numLikes = sampleTweet.numLikes,
                    elapsedTime = sampleTweet.longElapsedTime,
                    verified = true,
                )
            }
        }

        compareScreenshot(composeTestRule)
    }
}
