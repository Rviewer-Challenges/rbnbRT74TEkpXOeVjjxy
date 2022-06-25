package com.rumosoft.feature_timeline.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rumosoft.feature_timeline.domain.usecase.GetCommentsUseCase
import com.rumosoft.feature_timeline.domain.usecase.GetTweetUseCase
import com.rumosoft.feature_timeline.presentation.navigation.destination.PicturesDestination
import com.rumosoft.feature_timeline.presentation.screen.model.toTweetUI
import com.rumosoft.feature_timeline.presentation.viewmodel.state.CommentsState
import com.rumosoft.feature_timeline.presentation.viewmodel.state.DetailsState
import com.rumosoft.feature_timeline.presentation.viewmodel.state.TweetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTweetUseCase: GetTweetUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
) : ViewModel() {
    val uiState: StateFlow<DetailsState> get() = _uiState
    private val _uiState = MutableStateFlow(DetailsState())

    private val tweetId: Long = checkNotNull(savedStateHandle[PicturesDestination.tweetArg])

    fun retrieveTweetDetails() {
        viewModelScope.launch {
            val tweet = getTweetUseCase(tweetId)
            _uiState.update {
                it.copy(tweet = TweetState.Ready(tweet = tweet.toTweetUI()))
            }
            loadComments()
        }
    }

    private fun loadComments() {
        viewModelScope.launch {
            val comments = getCommentsUseCase(tweetId)
            _uiState.update { detailsState ->
                detailsState.copy(comments = CommentsState.Ready(
                    comments = comments.map { it.toTweetUI() }
                ))
            }
        }
    }
}