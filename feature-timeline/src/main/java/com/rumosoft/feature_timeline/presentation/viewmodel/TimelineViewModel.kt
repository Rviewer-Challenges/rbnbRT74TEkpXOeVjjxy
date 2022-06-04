package com.rumosoft.feature_timeline.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rumosoft.feature_timeline.domain.usecase.GetTimelineUseCase
import com.rumosoft.feature_timeline.presentation.viewmodel.state.Loading
import com.rumosoft.feature_timeline.presentation.viewmodel.state.Ready
import com.rumosoft.feature_timeline.presentation.viewmodel.state.TimelineState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
    private val getTimelineUseCase: GetTimelineUseCase,
) : ViewModel() {
    val uiState: StateFlow<TimelineState> get() = _uiState
    private val _uiState = MutableStateFlow<TimelineState>(Loading)

    fun retrieveTimeline() {
        if (_uiState.value is Loading) {
            viewModelScope.launch {
                val timeline = getTimelineUseCase()
                _uiState.update {
                    Ready(timeline)
                }
            }
        }
    }
}