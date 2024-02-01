package com.igorj.limboapp.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.limboapp.util.UiEvent
import com.igorj.limboapp.repository.interfaces.ChaptersRepository
import com.igorj.limboapp.repository.interfaces.StatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val statsRepository: StatsRepository,
    private val chaptersRepository: ChaptersRepository
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            val bestPeopleInGroupResult = statsRepository.getBestPeopleInGroup(0, 4)
                .getOrElse {
                    return@launch  // fix, so that miniChapters get loaded if this fails
                }
            state = state.copy(bestPeople = bestPeopleInGroupResult)

            val miniChaptersListResult = chaptersRepository.getChapters()
                .getOrElse {
                    return@launch
                }
            state = state.copy(miniChapters = miniChaptersListResult)
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnBestPersonClick -> {

            }

            is HomeEvent.OnBestPersonLongPress -> {

            }

            is HomeEvent.OnChapterClick -> {

            }

            is HomeEvent.OnChapterLongPress -> {

            }

            is HomeEvent.OnBottomNavBarClick -> {
                state = state.copy(
                    selectedScreen = event.route
                )
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.OnNavigate)
                }
            }

            is HomeEvent.OnFlickersClick -> {

            }

            is HomeEvent.OnProfileClick -> {

            }
        }
    }
}