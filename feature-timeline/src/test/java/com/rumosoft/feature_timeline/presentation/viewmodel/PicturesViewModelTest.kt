package com.rumosoft.feature_timeline.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.rumosoft.feature_timeline.domain.entity.Tweet
import com.rumosoft.feature_timeline.domain.usecase.GetTweetUseCase
import com.rumosoft.feature_timeline.presentation.navigation.destination.PicturesDestination
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(TestCoroutineExtension::class)
internal class PicturesViewModelTest {
    @Test
    fun `retrieveTimeline on View Model should invoke getTimelineUseCase`() = test {
        runTest {
            `given getTweetUseCase returns sampleTweet when invoked`()

            `when retrievePictures is invoked on the View Model`()

            `then getTweetUseCase is invoked`()
        }
    }

    private fun TestScope.`given getTweetUseCase returns sampleTweet when invoked`() {
        coEvery { getTweetUseCase(tweetId) } returns
                sampleTweet()
    }

    private fun TestScope.`when retrievePictures is invoked on the View Model`() {
        viewModel.retrievePictures()
    }

    private fun TestScope.`then getTweetUseCase is invoked`() {
        coVerify { getTweetUseCase(tweetId) }
    }

    private fun TestScope.sampleTweet() =
        Tweet(
            id = tweetId,
            username = "username",
            nickname = "nickname",
            profileImageUrl = "profileImageUrl",
            message = "message",
            numComments = 1,
            numRetweets = 2,
            numLikes = 3,
            elapsedTime = "elapsedtime",
            verified = false,
            images = listOf()
        )

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val tweetId: Long = 123L,
        val pictureId: Long = 123456L,
        val getTweetUseCase: GetTweetUseCase = mockk(),
        val viewModel: PicturesViewModel = PicturesViewModel(
            savedStateHandle = SavedStateHandle().apply {
                set(PicturesDestination.tweetArg, tweetId)
                set(PicturesDestination.pictureArg, pictureId)
            },
            getTweetUseCase = getTweetUseCase,
        )
    )
}