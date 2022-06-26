package com.rumosoft.feature_timeline.presentation.viewmodel.state

import com.rumosoft.feature_timeline.presentation.screen.model.TweetUI

data class DetailsState(
    val tweet: TweetState = TweetState.Loading,
    val comments: CommentsState = CommentsState.Loading,
)

sealed interface TweetState {
    object Loading : TweetState
    data class Ready(
        val tweet: TweetUI,
    ) : TweetState
}

sealed interface CommentsState {
    object Loading : CommentsState
    data class Ready(
        val comments: List<TweetUI>,
    ) : CommentsState
}
