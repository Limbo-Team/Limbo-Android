package com.igorj.limboapp.screen.stats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.limboapp.util.UiEvent
import com.igorj.limboapp.util.UiText
import com.igorj.limboapp.repository.interfaces.StatsRepository
import com.igorj.limboapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val statsRepository: StatsRepository
): ViewModel() {

    var state by mutableStateOf((StatsState()))
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun loadStats() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            val userStats = statsRepository.getUserStats()
                .getOrElse {
                    state = state.copy(isLoading = false)
                    return@launch
                }
            delay(1000)
            state = state.copy(
                userStats = userStats,
                isLoading = false
            )
        }
    }

    fun onEvent(event: StatsEvent) {
        when (event) {
            is StatsEvent.OnBottomNavBarClick -> {
                state = state.copy(
                    selectedScreen = event.route
                )
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.OnNavigate)
                }
            }

            else -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                        message = UiText.StringResource(R.string.function_not_implemented)
                    ))
                }
            }
        }
    }
}