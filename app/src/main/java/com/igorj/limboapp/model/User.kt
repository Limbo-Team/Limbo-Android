package com.igorj.limboapp.model

data class User(
    val firstName: String = "-",
    val lastName: String = "-",
    val email: String = "-",
    val image: String = "",
    val points: Int = 0,
)