package com.igorj.limboapp.screen.forgot_password.enter_verification_code

data class VerificationCodeState(
    val verificationCode: String = "",
    val isLoading: Boolean = false
)