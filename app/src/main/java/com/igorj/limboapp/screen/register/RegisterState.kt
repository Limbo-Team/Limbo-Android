package com.igorj.limboapp.screen.register

import com.igorj.limboapp.R

data class RegisterState(
    val email: String = "",
    val name: String = "",
    val surname: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val shouldHidePassword: Boolean = true,
    val passwordTextFieldIconId: Int = R.drawable.ic_gradient_password_invisible,
    val isStudent: Boolean = false,
    val studentId: String = "",
    val isTryingToRegister: Boolean = false
)