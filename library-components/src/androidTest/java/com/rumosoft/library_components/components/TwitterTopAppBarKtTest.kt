package com.rumosoft.library_components.components

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.karumi.shot.ScreenshotTest
import com.rumosoft.library_components.R
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme
import io.mockk.coJustRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

internal class TwitterTopAppBarKtTest : ScreenshotTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tweetHeader_whenTitleProvided_showsTitle() {
        lateinit var titleContentDescription: String

        composeTestRule.setContent {
            titleContentDescription = stringResource(id = R.string.twitter)
            TwitterMirroringTheme {
                TwitterTopAppBar(
                    title = { TwitterTitle() }
                )
            }
        }

        compareScreenshot(composeTestRule)
        composeTestRule.onNodeWithContentDescription(titleContentDescription)
            .assertIsDisplayed()
    }

    @Test
    fun tweetHeader_whenBackNavigationButtonProvided_canNavigateBack() {
        lateinit var backContentDescription: String
        val onBackClick: () -> Unit = mockk()
        coJustRun { onBackClick() }

        composeTestRule.setContent {
            backContentDescription = stringResource(id = R.string.back)
            TwitterMirroringTheme {
                TwitterTopAppBar(
                    navigationIcon = {
                        BackNavigationButton(
                            onBackClick = onBackClick
                        )
                    }
                )
            }
        }

        compareScreenshot(composeTestRule)
        composeTestRule.onNodeWithContentDescription(backContentDescription)
            .performClick()
        verify { onBackClick() }
    }
}
