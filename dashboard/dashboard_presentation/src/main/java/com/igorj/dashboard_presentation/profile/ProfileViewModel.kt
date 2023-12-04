package com.igorj.dashboard_presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.core.util.UiEvent
import com.igorj.dashboard_domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            val userResult = userRepository.getUser()
                .getOrElse {
                    return@launch
                }
            state = state.copy(user = userResult)
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
        }
    }
}