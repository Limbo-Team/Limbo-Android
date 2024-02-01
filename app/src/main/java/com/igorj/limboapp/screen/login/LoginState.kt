package com.igorj.limboapp.screen.login

import com.igorj.limboapp.R

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isTryingToLogin: Boolean = false,
    val shouldHidePassword: Boolean = true,
    val passwordTextFieldIconId: Int = R.drawable.ic_gradient_password_invisible
)
