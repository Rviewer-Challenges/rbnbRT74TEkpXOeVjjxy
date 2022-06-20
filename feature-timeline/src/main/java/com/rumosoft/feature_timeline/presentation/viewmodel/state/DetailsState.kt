package com.rumosoft.feature_timeline.presentation.viewmodel.state

import com.rumosoft.feature_timeline.presentation.screen.model.TweetUI

sealed interface DetailsState {
    object Loading : DetailsState
    data class Ready(
        val tweet: TweetUI,
    ) : DetailsState
}
