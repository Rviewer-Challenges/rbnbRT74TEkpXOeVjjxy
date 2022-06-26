package com.rumosoft.feature_timeline.presentation.viewmodel.state

import com.rumosoft.feature_timeline.presentation.screen.model.TweetUI

sealed interface TimelineState
object Loading : TimelineState
data class Ready(
    val tweets: List<TweetUI>
) : TimelineState
