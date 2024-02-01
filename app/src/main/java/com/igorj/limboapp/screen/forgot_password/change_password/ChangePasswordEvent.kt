package com.igorj.limboapp.screen.forgot_password.change_password

sealed class ChangePasswordEvent {
    data class OnNewPasswordChange(val newPassword: String): com.igorj.limboapp.screen.forgot_password.change_password.ChangePasswordEvent()
    data class OnConfirmNewPasswordChange(val confirmNewPassword: String): com.igorj.limboapp.screen.forgot_password.change_password.ChangePasswordEvent()
    object OnButtonClick: com.igorj.limboapp.screen.forgot_password.change_password.ChangePasswordEvent()
}