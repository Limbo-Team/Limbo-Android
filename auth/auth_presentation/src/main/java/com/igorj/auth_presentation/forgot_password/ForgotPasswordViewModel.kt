package com.igorj.auth_presentation.forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.core.domain.auth.AuthAPI
import com.igorj.core.util.UiEvent
import com.igorj.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authAPI: AuthAPI
): ViewModel() {
    var state by mutableStateOf(ForgotPasswordState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.OnEmailChange -> {
                state = state.copy(email = event.email)
            }

            is ForgotPasswordEvent.OnButtonClick -> {
                state = state.copy(isLoading = true)
                viewModelScope.launch {
                    val result = authAPI.forgotPassword(state.email)
                    delay(1000)
                    if (result) {
                        _uiEvent.send(UiEvent.ShowSnackbar(UiText.DynamicString("Email sent")))
                    } else {
                        _uiEvent.send(UiEvent.ShowSnackbar(UiText.DynamicString("Error, couldn't send email")))
                    }
                    state = state.copy(isLoading = false)
                }
            }
        }
    }
}