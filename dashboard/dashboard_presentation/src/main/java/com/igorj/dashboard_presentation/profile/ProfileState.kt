package com.igorj.dashboard_presentation.profile

import com.igorj.dashboard_domain.model.User

data class ProfileState(
    val user: User = User(),
    val selectedScreen: String = "profile",
    val flickers: Int = 0,
    val oldPassword: String = "",
    val newPassword: String = "",
    val newPasswordConfirmation: String = "",
    val isLoggingOut: Boolean = false,
    val isChangingPassword: Boolean = false
)