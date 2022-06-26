package com.rumosoft.library_components.components

import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import com.rumosoft.library_components.components.sampledata.SampleTweetData
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme
import org.junit.Rule
import org.junit.Test

internal class TweetTimelineTest : ScreenshotTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tweetTimeline_whenUnverifiedUser_comparesScreenshot() {
        composeTestRule.setContent {
            val sampleTweet = remember { SampleTweetData.sampleTweet() }
            TwitterMirroringTheme {
                TweetTimeline(
                    tweetId = 1,
                    profileImageUrl = sampleTweet.profileImageUrl,
                    username = sampleTweet.username,
                    nickname = sampleTweet.nickname,
                    message = sampleTweet.message,
                    numComments = sampleTweet.numComments,
                    totalRetweets = sampleTweet.numRetweets,
                    numLikes = sampleTweet.numLikes,
                    elapsedTime = sampleTweet.shortElapsedTime,
                )
            }
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun tweetTimeline_whenVerifiedUser_comparesScreenshot() {
        composeTestRule.setContent {
            val sampleTweet = remember { SampleTweetData.sampleTweet() }
            TwitterMirroringTheme {
                TweetTimeline(
                    tweetId = 1,
                    profileImageUrl = sampleTweet.profileImageUrl,
                    username = sampleTweet.username,
                    nickname = sampleTweet.nickname,
                    message = sampleTweet.message,
                    numComments = sampleTweet.numComments,
                    totalRetweets = sampleTweet.numRetweets,
                    numLikes = sampleTweet.numLikes,
                    elapsedTime = sampleTweet.shortElapsedTime,
                    verified = true,
                )
            }
        }

        compareScreenshot(composeTestRule)
    }
}
