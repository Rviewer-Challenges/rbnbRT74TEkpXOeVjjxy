package com.rumosoft.library_components.components

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rumosoft.library_components.R
import com.rumosoft.library_components.components.model.TweetActionComments
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

internal class ActionButtonKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun actionButton_showsValue() {
        val numComments = Random.nextInt(1, 100)

        composeTestRule.setContent {
            ActionButton(TweetActionComments(numComments.toString()))
        }

        composeTestRule.onNodeWithText(numComments.toString())
            .assertIsDisplayed()
    }

    @Test
    fun actionButton_callsLambdaOnClick() {
        val numComments = Random.nextInt(1, 100).toString()
        val onClickLambda: () -> Unit = mockk<() -> Unit>().also {
            justRun { it() }
        }
        lateinit var commentsContentDescription: String

        composeTestRule.setContent {
            commentsContentDescription = stringResource(R.string.comments)
            ActionButton(
                action = TweetActionComments(numComments),
                onClick = onClickLambda,
            )
        }

        composeTestRule.onNodeWithContentDescription(commentsContentDescription)
            .performClick()

        verify { onClickLambda() }
    }
}