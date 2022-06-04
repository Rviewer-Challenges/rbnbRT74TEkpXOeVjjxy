package com.rumosoft.feature_timeline.presentation.viewmodel.state

import com.rumosoft.feature_timeline.domain.entity.Tweet

sealed class TimelineState
object Loading: TimelineState()
data class Ready(
    val tweets: List<Tweet>
): TimelineState()
