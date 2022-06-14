package com.rumosoft.feature_timeline.domain.usecase

import com.rumosoft.feature_timeline.domain.repo_interfaces.TweetsRepository
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
internal class GetTweetUseCaseTest {
    @Test
    fun `invoking GetTweetsUseCase invokes repo`() = test {
        runTest {
            `given repo fetchTweet runs`()

            `when the use case gets invoked`()

            `then repo fetchTweet gets invoked`()
        }
    }

    private fun TestScope.`given repo fetchTweet runs`() {
        coJustRun { repo.fetchTweet(tweetId) }
    }

    private suspend fun TestScope.`when the use case gets invoked`() {
        sut(tweetId)
    }

    private fun TestScope.`then repo fetchTweet gets invoked`() {
        coVerify { repo.fetchTweet(tweetId) }
    }

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val tweetId: Long = Random.nextLong(1L, 2000L),
        val repo: TweetsRepository = mockk(),
        val sut: GetTweetUseCase = GetTweetUseCase(
            repository = repo,
        )
    )
}