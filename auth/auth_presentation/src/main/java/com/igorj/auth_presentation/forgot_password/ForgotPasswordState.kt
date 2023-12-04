package com.igorj.auth_presentation.forgot_password

data class ForgotPasswordState(
    val email: String = "",
    val isLoading: Boolean = false
)