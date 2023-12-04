package com.igorj.auth_presentation.forgot_password.change_password

data class ChangePasswordState(
    val newPassword: String = "",
    val confirmNewPassword: String = "",
    val isLoading: Boolean = false
)
