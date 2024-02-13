package com.igorj.limboapp.screen.finish_quiz

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.igorj.limboapp.model.FinishedQuizResponse
import com.igorj.limboapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinishQuizViewModel @Inject constructor(

): ViewModel() {

    var state by mutableStateOf(FinishQuizState())
        private set

    val gson = Gson()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun loadFinishedQuizResponse(finishedQuizResponseAsJson: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            delay(1000)
            val finishedQuizResponse = if (finishedQuizResponseAsJson == "null") {
                null
            } else {
                gson.fromJson(finishedQuizResponseAsJson, FinishedQuizResponse::class.java)
            }
            state = state.copy(
                finishedQuizResponse = finishedQuizResponse,
                isLoading = false
            )
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