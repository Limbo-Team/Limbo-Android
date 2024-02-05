package com.igorj.limboapp.screen.home

import android.util.Log
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
import kotlinx.coroutines.delay
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

    fun loadBestPeople() {
        state = state.copy(isLoadingBestPeople = true)
        viewModelScope.launch {
            val bestPeopleInGroupResult = statsRepository.getBestPeopleInGroup(0, 4)
                .getOrElse {
                    state = state.copy(isLoadingBestPeople = false)
                    return@launch
                }
            delay(1000)
            state = state.copy(
                bestPeople = bestPeopleInGroupResult,
                isLoadingBestPeople = false
            )
        }
    }

    fun loadMiniChapters() {
        state = state.copy(isLoadingMiniChapters = true)
        viewModelScope.launch {
            val miniChaptersListResult = chaptersRepository.getChapters()
                .getOrElse {
                    state = state.copy(isLoadingMiniChapters = false)
                    Log.d("LOGCAT", "Failed to get chapters")
                    return@launch
                }
            delay(1000)
            state = state.copy(
                miniChapters = miniChaptersListResult,
                isLoadingMiniChapters = false
            )
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