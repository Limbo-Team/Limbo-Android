package com.igorj.limboapp.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.limboapp.R
import com.igorj.limboapp.repository.interfaces.AuthAPI
import com.igorj.limboapp.use_case.FilterOutStudentIdDigits
import com.igorj.limboapp.util.UiEvent
import com.igorj.limboapp.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authAPI: AuthAPI,
    private val filterOutStudentIdDigits: FilterOutStudentIdDigits
) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnEmailChange -> {
                state = state.copy(email = event.email)
            }

            is RegisterEvent.OnNameChange -> {
                state = state.copy(name = event.name)
            }

            is RegisterEvent.OnSurnameChange -> {
                state = state.copy(surname = event.surname)
            }

            is RegisterEvent.OnPasswordChange -> {
                state = state.copy(password = event.password)
            }

            is RegisterEvent.OnRepeatPasswordChange -> {
                state = state.copy(repeatPassword = event.repeatPassword)
            }

            is RegisterEvent.OnTogglePasswordVisibility -> {
                val shouldHidePasswordAfterClick = !state.shouldHidePassword
                state = state.copy(
                    shouldHidePassword = shouldHidePasswordAfterClick,
                    passwordTextFieldIconId = if (shouldHidePasswordAfterClick) {
                        R.drawable.ic_gradient_password_invisible
                    } else R.drawable.ic_gradient_password_visible
                )
            }

            is RegisterEvent.OnToggleIsStudent -> {
                state = state.copy(isStudent = event.isChecked)
            }

            is RegisterEvent.OnStudentIdChange -> {
                if (event.studentId.length <= 6) {
                    state = state.copy(studentId = filterOutStudentIdDigits(event.studentId))
                }
            }

            is RegisterEvent.OnRegisterClick -> {
                viewModelScope.launch {
                    state = state.copy(isTryingToRegister = true)
                    authAPI.register(
                        firstName = state.name,
                        lastName = state.surname,
                        password = state.password,
                        email = state.email,
                        onResult = { response ->
                            viewModelScope.launch {
                                state = state.copy(isTryingToRegister = false)
                                if (response) {
                                    _uiEvent.send(
                                        UiEvent.ShowSnackbar(
                                            UiText.DynamicString("Created account")
                                        )
                                    )
                                    _uiEvent.send(UiEvent.OnNavigate)
                                } else {
                                    _uiEvent.send(
                                        UiEvent.ShowSnackbar(
                                            UiText.DynamicString("Failed to register")
                                        )
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}