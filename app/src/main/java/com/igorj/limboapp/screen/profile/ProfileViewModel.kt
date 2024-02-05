package com.igorj.limboapp.screen.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.limboapp.repository.interfaces.AuthAPI
import com.igorj.limboapp.util.UiEvent
import com.igorj.limboapp.repository.interfaces.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authAPI: AuthAPI
): ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun loadProfileInfo() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            val userResult = userRepository.getUser()
                .getOrElse {
                    state = state.copy(isLoading = false)
                    return@launch
                }
            delay(1000)
            state = state.copy(
                user = userResult,
                isLoading = false
            )
        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.OnBottomNavBarClick -> {
                state = state.copy(
                    selectedScreen = event.route
                )
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.OnNavigate)
                }
            }
            is ProfileEvent.OnFlickersClick -> {

            }
            is ProfileEvent.OnProfileClick -> {

            }
            is ProfileEvent.OnChangePasswordClick -> {

            }
            is ProfileEvent.OnExchangeFlickersClick -> {

            }
            is ProfileEvent.OnOldPasswordChange -> {
                state = state.copy(oldPassword = event.oldPassword)
            }
            is ProfileEvent.OnNewPasswordChange -> {
                state = state.copy(newPassword = event.newPassword)
            }
            is ProfileEvent.OnNewPasswordConfirmationChange -> {
                state = state.copy(newPasswordConfirmation = event.newPasswordConfirmation)
            }
            is ProfileEvent.OnLogoutClick -> {
                viewModelScope.launch {
                    authAPI.logout()
                    state = state.copy(
                        selectedScreen = "login"
                    )
                    _uiEvent.send(UiEvent.OnNavigate)
                }
            }
        }
    }
}