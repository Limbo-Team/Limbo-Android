package com.igorj.dashboard_presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.core.util.UiEvent
import com.igorj.dashboard_domain.repository.ChaptersRepository
import com.igorj.dashboard_domain.repository.StatsRepository
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
            state = state.copy(bestPeopleList = bestPeopleInGroupResult)

            val miniChaptersListResult = chaptersRepository.getChapters(4)
                .getOrElse {
                    return@launch
                }
            state = state.copy(miniChaptersList = miniChaptersListResult)
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