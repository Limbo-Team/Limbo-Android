package com.igorj.limboapp.screen.quizzes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.limboapp.repository.interfaces.ChaptersRepository
import com.igorj.limboapp.screen.chapters.ChaptersState
import com.igorj.limboapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizzesViewModel @Inject constructor(
    private val chaptersRepository: ChaptersRepository
): ViewModel() {

    var state by mutableStateOf(QuizzesState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun loadQuizzes(chapterId: String) {
        viewModelScope.launch {
            val quizzes = chaptersRepository.getChapterQuizzes(chapterId)
                .getOrElse {
                    return@launch
                }
            state = state.copy(quizzes = quizzes)
        }
    }

    fun onEvent(event: QuizzesEvent) {
        when (event) {
            is QuizzesEvent.OnQuizClick -> {

            }

            is QuizzesEvent.OnQuizLongPress -> {

            }

            is QuizzesEvent.OnBottomNavBarClick -> {
                state = state.copy(
                    selectedScreen = event.route
                )
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.OnNavigate)
                }
            }

            is QuizzesEvent.OnFlickersClick -> {

            }

            is QuizzesEvent.OnProfileClick -> {

            }
        }
    }
}