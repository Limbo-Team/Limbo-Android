package com.igorj.dashboard_presentation.stats

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.core.util.UiEvent
import com.igorj.core.util.UiText
import com.igorj.dashboard_domain.repository.StatsRepository
import com.igorj.core.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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

    init {
        viewModelScope.launch {
            val userStats = statsRepository.getUserStats(1)
            if (userStats.isSuccess) {
                state = state.copy(
                    userStats = userStats.getOrElse {
                        return@launch
                    }

                )
            }
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
                    _uiEvent.send(UiEvent.ShowSnackbar(
                        message = UiText.StringResource(R.string.function_not_implemented)
                    ))
                }
            }
        }
    }
}