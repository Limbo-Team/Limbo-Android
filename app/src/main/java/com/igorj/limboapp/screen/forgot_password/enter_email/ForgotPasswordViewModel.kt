package com.igorj.limboapp.screen.forgot_password.enter_email

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.limboapp.repository.interfaces.AuthAPI
import com.igorj.limboapp.util.UiEvent
import com.igorj.limboapp.util.UiText
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
                    val result = true
                    delay(1000)
                    if (result) {
                        state = state.copy(isLoading = false)
                        _uiEvent.send(UiEvent.OnNavigate)
                    } else {
                        state = state.copy(isLoading = false)
                        _uiEvent.send(UiEvent.ShowSnackbar(UiText.DynamicString("Error, couldn't send email")))
                    }
                }
            }
        }
    }
}