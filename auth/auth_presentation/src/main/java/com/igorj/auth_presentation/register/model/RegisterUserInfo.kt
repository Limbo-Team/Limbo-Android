package com.igorj.auth_presentation.register.model

data class RegisterUserInfo(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val studentId: String?
)