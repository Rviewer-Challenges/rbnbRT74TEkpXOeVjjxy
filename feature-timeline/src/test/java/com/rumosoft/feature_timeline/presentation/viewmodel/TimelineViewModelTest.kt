package com.rumosoft.feature_timeline.presentation.viewmodel

import com.rumosoft.feature_timeline.domain.usecase.GetTimelineUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(TestCoroutineExtension::class)
internal class TimelineViewModelTest {
    @Test
    fun `retrieveTimeline on View Model should invoke getTimelineUseCase`() = test {
        runTest {
            coEvery { getTimelineUseCase() } returns emptyList()

            viewModel.retrieveTimeline()

            coVerify { getTimelineUseCase() }
        }
    }

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val getTimelineUseCase: GetTimelineUseCase = mockk(),
        val viewModel: TimelineViewModel = TimelineViewModel(
            getTimelineUseCase = getTimelineUseCase,
        )
    )
}