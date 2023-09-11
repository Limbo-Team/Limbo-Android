package com.igorj.auth_presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.auth_domain.use_case.FilterOutStudentIdDigits
import com.igorj.core.R
import com.igorj.core.domain.auth.AuthAPI
import com.igorj.core.util.UiEvent
import com.igorj.core.util.UiText
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
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            message = UiText.StringResource(R.string.function_not_implemented)
                        )
                    )
                    state = state.copy(isTryingToRegister = false)
                }
            }
        }
    }
}