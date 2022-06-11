package com.rumosoft.feature_timeline.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rumosoft.feature_timeline.domain.usecase.GetTweetUseCase
import com.rumosoft.feature_timeline.presentation.navigation.destination.PicturesDestination
import com.rumosoft.feature_timeline.presentation.screen.model.toTweetUI
import com.rumosoft.feature_timeline.presentation.viewmodel.state.PicturesLoading
import com.rumosoft.feature_timeline.presentation.viewmodel.state.PicturesReady
import com.rumosoft.feature_timeline.presentation.viewmodel.state.PicturesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTweetUseCase: GetTweetUseCase,
) : ViewModel() {
    val uiState: StateFlow<PicturesState> get() = _uiState
    private val _uiState = MutableStateFlow<PicturesState>(PicturesLoading)

    private val tweetId: Long = checkNotNull(savedStateHandle[PicturesDestination.tweetArg])

    fun retrievePictures() {
        if (_uiState.value is PicturesLoading) {
            viewModelScope.launch {
                val tweet = getTweetUseCase(tweetId)
                _uiState.update {
                    PicturesReady(tweet.toTweetUI())
                }
            }
        }
    }
}
