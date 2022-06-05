package com.rumosoft.library_components.components

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.rumosoft.library_components.R
import org.junit.Rule
import org.junit.Test

internal class TwitterTopAppBarKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tweetHeader_showsTitle() {
        lateinit var titleContentDescription: String

        composeTestRule.setContent {
            titleContentDescription = stringResource(id = R.string.twitter)
            TwitterTopAppBar()
        }

        Thread.sleep(5_000)

        composeTestRule.onNodeWithContentDescription(titleContentDescription)
            .assertIsDisplayed()
    }
}