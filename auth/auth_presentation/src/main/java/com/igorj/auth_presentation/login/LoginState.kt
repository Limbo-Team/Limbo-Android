package com.igorj.auth_presentation.login

import com.igorj.core.R

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isTryingToLogin: Boolean = false,
    val shouldHidePassword: Boolean = true,
    val passwordTextFieldIconId: Int = R.drawable.ic_gradient_password_invisible
)
