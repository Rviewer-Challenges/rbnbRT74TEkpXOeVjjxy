package com.rumosoft.feature_timeline.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.rumosoft.feature_timeline.domain.entity.Tweet
import com.rumosoft.feature_timeline.domain.usecase.GetCommentsUseCase
import com.rumosoft.feature_timeline.domain.usecase.GetTweetUseCase
import com.rumosoft.feature_timeline.presentation.navigation.destination.PicturesDestination
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.Instant

@ExperimentalCoroutinesApi
@ExtendWith(TestCoroutineExtension::class)
internal class DetailsViewModelTest {
    @Test
    fun `retrieveTweetDetails on View Model should invoke getTweetUseCase`() = test {
        runTest {
            coEvery { getTweetUseCase(tweetId) } returns sampleTweet()
            coEvery { getCommentsUseCase(tweetId) } returns listOf(sampleTweet())

            viewModel.retrieveTweetDetails()

            coVerify { getTweetUseCase(tweetId) }
        }
    }

    @Test
    fun `retrieveTweetDetails on View Model should invoke getCommentsUseCase`() = test {
        runTest {
            coEvery { getTweetUseCase(tweetId) } returns sampleTweet()
            coEvery { getCommentsUseCase(tweetId) } returns listOf(sampleTweet())

            viewModel.retrieveTweetDetails()

            coVerify { getCommentsUseCase(tweetId) }
        }
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
            numQuoteTweets = 1,
            numLikes = 3,
            posted = Instant.now(),
            verified = false,
            images = listOf()
        )

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val tweetId: Long = 123L,
        val getTweetUseCase: GetTweetUseCase = mockk(),
        val getCommentsUseCase: GetCommentsUseCase = mockk(),
        val viewModel: DetailsViewModel = DetailsViewModel(
            savedStateHandle = SavedStateHandle().apply {
                set(PicturesDestination.tweetArg, tweetId)
            },
            getTweetUseCase = getTweetUseCase,
            getCommentsUseCase = getCommentsUseCase,
        )
    )
}