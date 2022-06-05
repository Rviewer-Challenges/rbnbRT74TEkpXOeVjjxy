package com.rumosoft.feature_timeline.presentation.viewmodel.state

import com.rumosoft.feature_timeline.presentation.screen.model.ScreenTweet

sealed class TimelineState
object Loading : TimelineState()
data class Ready(
    val tweets: List<ScreenTweet>
) : TimelineState()
