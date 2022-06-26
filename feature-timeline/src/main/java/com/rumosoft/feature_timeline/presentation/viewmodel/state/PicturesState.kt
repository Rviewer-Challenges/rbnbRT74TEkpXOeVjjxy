package com.rumosoft.feature_timeline.presentation.viewmodel.state

import com.rumosoft.feature_timeline.presentation.screen.model.TweetUI

sealed interface PicturesState
object PicturesLoading : PicturesState
data class PicturesReady(
    val tweet: TweetUI,
    val pictureId: Long,
) : PicturesState
