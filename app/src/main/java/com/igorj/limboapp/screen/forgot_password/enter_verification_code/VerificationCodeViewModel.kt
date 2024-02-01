package com.igorj.limboapp.screen.forgot_password.enter_verification_code

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
class VerificationCodeViewModel @Inject constructor(
    private val authAPI: AuthAPI
): ViewModel() {
    var state by mutableStateOf(VerificationCodeState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: VerificationCodeEvent) {
        when (event) {
            is VerificationCodeEvent.OnVerificationCodeChange -> {
                state = state.copy(verificationCode = event.verificationCode)
            }
            is VerificationCodeEvent.OnButtonClick -> {
                state = state.copy(isLoading = true)
                viewModelScope.launch {
                    delay(1000)
                    state = state.copy(isLoading = false)
                    _uiEvent.send(UiEvent.OnNavigate)
                }
            }
        }
    }
}