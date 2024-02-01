package com.igorj.limboapp.screen.forgot_password.enter_verification_code

sealed class VerificationCodeEvent {
    data class OnVerificationCodeChange(val verificationCode: String): VerificationCodeEvent()
    object OnButtonClick: VerificationCodeEvent()
}