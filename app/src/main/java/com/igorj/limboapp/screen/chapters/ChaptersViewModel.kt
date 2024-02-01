package com.igorj.limboapp.screen.chapters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.limboapp.util.UiEvent
import com.igorj.limboapp.repository.interfaces.ChaptersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChaptersViewModel @Inject constructor(
    private val chaptersRepository: ChaptersRepository
): ViewModel() {
    var state by mutableStateOf(ChaptersState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            val chaptersListResult = chaptersRepository.getChapters()
                .getOrElse {
                    return@launch
                }
            state = state.copy(chapters = chaptersListResult)
        }
    }

    fun onEvent(event: ChaptersEvent) {
        when (event) {
            is ChaptersEvent.OnChapterClick -> {

            }

            is ChaptersEvent.OnChapterLongPress -> {

            }

            is ChaptersEvent.OnBottomNavBarClick -> {
                state = state.copy(
                    selectedScreen = event.route
                )
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.OnNavigate)
                }
            }

            is ChaptersEvent.OnFlickersClick -> {

            }

            is ChaptersEvent.OnProfileClick -> {

            }
        }
    }
}