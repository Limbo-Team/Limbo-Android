package com.igorj.limboapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.limboapp.repository.interfaces.AuthAPI
import com.igorj.limboapp.repository.interfaces.UserRepository
import com.igorj.limboapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authApi: AuthAPI,
    private val userRepository: UserRepository
): ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun updateTopBarInfo() {
        val token = authApi.getToken()
        if (token.isNotBlank()) {
            viewModelScope.launch {
                val userInfoFromServer = userRepository.getUser().getOrNull()
                if (userInfoFromServer != null) {
                    state = state.copy(
                        userInfo = userInfoFromServer,
                        flickers = userInfoFromServer.points
                    )
                }
            }
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnFlickersClick -> {
                Log.d("LOGCAT", "${state.flickers}")
            }
            is MainEvent.OnBottomNavBarClick -> {

            }
            is MainEvent.OnLogoutClick -> {
                authApi.logout()
                state = state.copy(
                    isLoggingOut = true
                )
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.OnNavigate)
                }
            }
        }
    }
}