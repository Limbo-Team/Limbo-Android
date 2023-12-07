package com.igorj.quiz_presentation.playing_quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.core.util.UiEvent
import com.igorj.quiz_domain.repository.QuestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.min

@HiltViewModel
class PlayingScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val questionsRepository: QuestionsRepository
): ViewModel() {

    var state by mutableStateOf(PlayingQuizState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            state = state.copy(chapterId = savedStateHandle.get<Int>("chapterId") ?: 0)
            if (state.chapterId != 0) {
                val questions = questionsRepository.getQuestions(state.chapterId, 123).getOrElse {
                    return@launch
                }
                state = state.copy(questions = questions)
            }
        }
    }

    fun onEvent(event: PlayingQuizEvent) {
        when (event) {
            is PlayingQuizEvent.OnStart -> {

            }
            is PlayingQuizEvent.OnNextQuestionClick -> {
                if (state.selectedAnswerPosition == -1) {
                    return
                }
                state = state.copy(
                    answers = state.answers + event.answer,
                    currentQuestionIndex = state.currentQuestionIndex + 1,
                    selectedAnswerPosition = -1,
                    timeLeft = min(60f, state.timeLeft + 5f),
                )
            }
            is PlayingQuizEvent.OnFinish -> {
                // send answers to backend server
            }
            is PlayingQuizEvent.OnAnswerClick -> {
                state = state.copy(
                    selectedAnswerPosition = event.position
                )
            }
            is PlayingQuizEvent.OnTimeTick -> {
                state = state.copy(
                    timeLeft = state.timeLeft - 0.1f
                )
            }
        }
    }
}