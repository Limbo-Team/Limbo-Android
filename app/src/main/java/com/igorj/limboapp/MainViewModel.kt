package com.igorj.limboapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.limboapp.repository.interfaces.AuthAPI
import com.igorj.limboapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authApi: AuthAPI
): ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun loadInitialUserInfo() {
        val token = authApi.getToken()
        if (token.isNotBlank()) {
            viewModelScope.launch {
                val user = authApi.getUserInfo()
                state = state.copy(
                    userInfo = user
                )
            }
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnFlickersClick -> {

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