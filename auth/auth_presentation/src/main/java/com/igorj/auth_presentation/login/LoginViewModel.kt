package com.igorj.auth_presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import com.igorj.core.R
import com.igorj.core.domain.auth.AuthAPI
import com.igorj.core.util.UiText
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authApi: AuthAPI
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> {
                state = state.copy(email = event.email)
            }

            is LoginEvent.OnPasswordChange -> {
                state = state.copy(password = event.password)
            }

            is LoginEvent.OnTogglePasswordVisibility -> {
                val shouldHidePasswordAfterClick = !state.shouldHidePassword
                state = state.copy(
                    shouldHidePassword = shouldHidePasswordAfterClick,
                    passwordTextFieldIconId = if (shouldHidePasswordAfterClick) {
                        R.drawable.ic_gradient_password_invisible
                    } else R.drawable.ic_gradient_password_visible
                )
            }

            is LoginEvent.OnLoginClick -> {
                viewModelScope.launch {
                    if (event.email.isBlank() || event.password.isBlank()) {
                        _uiEvent.send(
                            UiEvent.ShowSnackbar(
                                UiText.StringResource(R.string.empty_credentials)
                            )
                        )
                        return@launch
                    }
                    state = state.copy(isTryingToLogin = true)
                    authApi.saveUsername(event.email)
                    authApi.savePassword(event.password)
                    authApi.authorize(
                        onResult = { response ->
                            viewModelScope.launch {
                                state = state.copy(isTryingToLogin = false)
                                if (response.isBlank()) {
                                    _uiEvent.send(
                                        UiEvent.ShowSnackbar(
                                            UiText.StringResource(R.string.failed_to_log_in)
                                        )
                                    )
                                } else {
                                    _uiEvent.send(UiEvent.OnNavigate)
                                }
                            }
                        }
                    )
                }
            }
        }
    }

}