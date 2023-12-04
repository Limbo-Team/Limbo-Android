package com.igorj.dashboard_presentation.profile

sealed class ProfileEvent {
    data class OnBottomNavBarClick(val route: String): ProfileEvent()
    object OnFlickersClick: ProfileEvent()
    object OnProfileClick: ProfileEvent()
    data class OnOldPasswordChange(val oldPassword: String): ProfileEvent()
    data class OnNewPasswordChange(val newPassword: String): ProfileEvent()
    data class OnNewPasswordConfirmationChange(val newPasswordConfirmation: String): ProfileEvent()
    object OnChangePasswordClick : ProfileEvent()
    object OnExchangeFlickersClick: ProfileEvent()
}
