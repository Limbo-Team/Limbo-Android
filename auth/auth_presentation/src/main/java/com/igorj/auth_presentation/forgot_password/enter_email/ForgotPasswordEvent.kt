package com.igorj.auth_presentation.forgot_password.enter_email

sealed class ForgotPasswordEvent {
    data class OnEmailChange(val email: String): ForgotPasswordEvent()
    object OnButtonClick: ForgotPasswordEvent()
}