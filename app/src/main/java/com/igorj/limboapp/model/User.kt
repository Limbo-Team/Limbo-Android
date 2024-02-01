package com.igorj.limboapp.model

data class User(
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val imageUrl: String = "",
    val flickers: Int = 0,
    val studentId: Int? = null,
    val studentGroupId: Int? = null
)