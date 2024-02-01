package com.igorj.limboapp.repository.interfaces

interface AuthAPI {
    companion object {
        const val BASE_URL = "https://limbo-backend.onrender.com"

        const val USERNAME_KEY = "username_key"
        const val PASSWORD_KEY = "password_key"
        const val TOKEN_KEY = "token_key"
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

    fun getUsername(): String
    fun getPassword(): String
    fun getToken(): String

    fun logout()
}