package com.igorj.limboapp.screen.profile

import com.igorj.limboapp.model.User

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