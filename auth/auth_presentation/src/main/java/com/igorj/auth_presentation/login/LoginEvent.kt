package com.igorj.auth_presentation.login

sealed class LoginEvent {
    data class OnEmailChange(val email: String): LoginEvent()
    data class OnPasswordChange(val password: String): LoginEvent()
    object OnTogglePasswordVisibility: LoginEvent()
    data class OnLoginClick(val email: String, val password: String): LoginEvent()
}