package com.igorj.limboapp.repository.interfaces

import com.igorj.limboapp.model.User

interface AuthAPI {
    companion object {
        const val BASE_URL = "https://limbo-backend.onrender.com"

        const val USERNAME_KEY = "username_key"
        const val PASSWORD_KEY = "password_key"
        const val TOKEN_KEY = "token_key"
        const val USER_INFO_KEY = "user_info_key"
    }
    suspend fun authorize(onResult: (String) -> Unit)
    suspend fun register(
        firstName: String,
        lastName: String,
        password: String,
        email: String,
        onResult: (Boolean) -> Unit
    )

    fun saveUsername(username: String)
    fun savePassword(password: String)
    fun saveToken(token: String)
    fun saveUserInfo(user: User)

    fun getUsername(): String
    fun getPassword(): String
    fun getToken(): String
    fun getUserInfo(): User

    fun logout()
}