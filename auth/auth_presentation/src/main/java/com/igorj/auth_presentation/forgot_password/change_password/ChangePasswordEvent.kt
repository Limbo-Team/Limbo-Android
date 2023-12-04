package com.igorj.auth_presentation.forgot_password.change_password

sealed class ChangePasswordEvent {
    data class OnNewPasswordChange(val newPassword: String): ChangePasswordEvent()
    data class OnConfirmNewPasswordChange(val confirmNewPassword: String): ChangePasswordEvent()
    object OnButtonClick: ChangePasswordEvent()
}