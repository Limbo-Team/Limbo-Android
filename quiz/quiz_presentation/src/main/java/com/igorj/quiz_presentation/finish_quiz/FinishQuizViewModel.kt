package com.igorj.quiz_presentation.finish_quiz

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinishQuizViewModel @Inject constructor(

): ViewModel() {

    var state by mutableStateOf(FinishQuizState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            state = state.copy(gainedPoints = 10)
        }
    }

    fun onEvent(event: FinishQuizEvent) {
        when (event) {
            is FinishQuizEvent.OnFinish -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.OnNavigate)
                }
            }
            is FinishQuizEvent.OnBackButtonClick -> {
                Log.d("LOGCAT", "OnBackButtonClick")
            }
        }
    }

}