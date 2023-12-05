package com.igorj.quiz_presentation.playing_quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.core.util.UiEvent
import com.igorj.quiz_domain.repository.QuestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayingScreenViewModel @Inject constructor(
    private val questionsRepository: QuestionsRepository
): ViewModel() {

    var state by mutableStateOf(PlayingQuizState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            val questions = questionsRepository.getQuestions(123).getOrElse {
                return@launch
            }
            state = state.copy(questions = questions)
        }
    }

    fun onEvent(event: PlayingQuizEvent) {
        when (event) {
            is PlayingQuizEvent.OnStart -> {

            }
            is PlayingQuizEvent.OnNextQuestion -> {

            }
            is PlayingQuizEvent.OnFinish -> {

            }
            is PlayingQuizEvent.OnAnswer -> {

            }
        }
    }
}