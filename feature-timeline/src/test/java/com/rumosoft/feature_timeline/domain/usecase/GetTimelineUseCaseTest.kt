package com.rumosoft.feature_timeline.domain.usecase

import com.rumosoft.feature_timeline.domain.repo_interfaces.TweetsRepository
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class GetTimelineUseCaseTest {
    @Test
    fun `invoking GetTimelineUseCase invokes repo`() = test {
        runTest {
            `given repo fetchTweet runs`()

            `when the use case gets invoked`()

            `then repo fetchTweet gets invoked`()
        }
    }

    private fun TestScope.`given repo fetchTweet runs`() {
        coJustRun { repo.fetchTweets() }
    }

    private suspend fun TestScope.`when the use case gets invoked`() {
        sut()
    }

    private fun TestScope.`then repo fetchTweet gets invoked`() {
        coVerify { repo.fetchTweets() }
    }

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val repo: TweetsRepository = mockk(),
        val sut: GetTimelineUseCase = GetTimelineUseCase(
            repository = repo,
        )
    )
}