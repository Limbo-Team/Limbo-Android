package com.igorj.auth_presentation.forgot_password.enter_email

data class ForgotPasswordState(
    val email: String = "",
    val isLoading: Boolean = false
)