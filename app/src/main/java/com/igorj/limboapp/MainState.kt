package com.igorj.limboapp

import com.igorj.limboapp.model.User

data class MainState(
    val flickers: Int = 0,
    val userInfo: User = User(),
    val isLoggingOut: Boolean = false
)
