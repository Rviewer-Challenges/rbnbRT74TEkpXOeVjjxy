package com.rumosoft.library_components.components

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.rumosoft.library_components.R
import com.rumosoft.library_components.components.model.TweetActionsClick
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

internal class TweetActionButtonsKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tweetActionButtons_showsAll4Buttons() {
        val comments = Random.nextInt(1, 100)
        val retweets = Random.nextInt(1, 100)
        val likes = Random.nextInt(1, 100)
        val actionsClick: TweetActionsClick =
            emptyActionsClick()
        lateinit var commentsContentDescription: String
        lateinit var retweetsContentDescription: String
        lateinit var likesContentDescription: String
        lateinit var shareContentDescription: String

        composeTestRule.setContent {
            commentsContentDescription = stringResource(R.string.comments)
            retweetsContentDescription = stringResource(R.string.comments)
            likesContentDescription = stringResource(R.string.comments)
            shareContentDescription = stringResource(R.string.comments)
            TweetActionButtons(
                numComments = comments.toString(),
                numRetweets = retweets.toString(),
                numLikes = likes.toString(),
                onActionsClick = actionsClick
            )
        }

        composeTestRule.onNodeWithContentDescription(commentsContentDescription)
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(retweetsContentDescription)
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(likesContentDescription)
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(shareContentDescription)
            .assertIsDisplayed()
    }

    private fun emptyActionsClick() = object : TweetActionsClick {
        override fun onCommentsClick() {}

        override fun onRetweetsClick() {}

        override fun onLikesClick() {}

        override fun onShareClick() {}
    }
}