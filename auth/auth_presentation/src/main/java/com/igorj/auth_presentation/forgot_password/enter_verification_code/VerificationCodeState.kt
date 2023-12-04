package com.igorj.auth_presentation.forgot_password.enter_verification_code

data class VerificationCodeState(
    val verificationCode: String = "",
    val isLoading: Boolean = false
)