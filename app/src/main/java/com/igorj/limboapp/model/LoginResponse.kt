package com.igorj.limboapp.model

data class LoginResponse(
    val authToken: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val image: String,
    val points: Int,
)
