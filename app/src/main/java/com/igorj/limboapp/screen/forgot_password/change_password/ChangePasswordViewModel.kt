package com.igorj.limboapp.screen.forgot_password.change_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.limboapp.repository.interfaces.AuthAPI
import com.igorj.limboapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val authAPI: AuthAPI
): ViewModel() {
    var state by mutableStateOf(ChangePasswordState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: com.igorj.limboapp.screen.forgot_password.change_password.ChangePasswordEvent) {
        when (event) {
            is com.igorj.limboapp.screen.forgot_password.change_password.ChangePasswordEvent.OnNewPasswordChange -> {
                state = state.copy(newPassword = event.newPassword)
            }
            is com.igorj.limboapp.screen.forgot_password.change_password.ChangePasswordEvent.OnConfirmNewPasswordChange -> {
                state = state.copy(confirmNewPassword = event.confirmNewPassword)
            }
            is com.igorj.limboapp.screen.forgot_password.change_password.ChangePasswordEvent.OnButtonClick -> {
                state = state.copy(isLoading = true)
                viewModelScope.launch {
                    delay(1000)
                    state = state.copy(isLoading = false)
                }
            }
        }
    }
}