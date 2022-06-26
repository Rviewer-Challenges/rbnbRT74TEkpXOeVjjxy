package com.rumosoft.feature_timeline

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.rumosoft.feature_timeline.presentation.screen.state.BuildUI
import com.rumosoft.feature_timeline.presentation.viewmodel.state.Loading
import com.rumosoft.feature_timeline.presentation.viewmodel.state.Ready
import com.rumosoft.feature_timeline.sampledata.SampleScreenTweetData.sampleScreenTweet
import org.junit.Rule
import org.junit.Test

internal class TimelineScreenBuildUIKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingStateBuildUi_showsLoading() {
        lateinit var loadingContentDescription: String

        composeTestRule.setContent {
            loadingContentDescription = stringResource(id = R.string.loading)
            val state = Loading

            state.BuildUI()
        }

        composeTestRule.onNodeWithContentDescription(loadingContentDescription)
            .assertIsDisplayed()
    }

    @Test
    fun readyStateBuildUi_doesNotShowLoading() {
        lateinit var loadingContentDescription: String

        composeTestRule.setContent {
            loadingContentDescription = stringResource(id = R.string.loading)
            val state = Ready(emptyList())

            state.BuildUI()
        }

        composeTestRule.onNodeWithContentDescription(loadingContentDescription)
            .assertDoesNotExist()
    }

    @Test
    fun readyStateBuildUi_showsReadyContent() {
        lateinit var timelineContentDescription: String

        composeTestRule.setContent {
            timelineContentDescription = stringResource(id = R.string.timeline)
            val state = Ready(
                listOf(
                    sampleScreenTweet()
                )
            )

            state.BuildUI()
        }

        composeTestRule.onNodeWithContentDescription(timelineContentDescription)
            .assertIsDisplayed()
    }
}
