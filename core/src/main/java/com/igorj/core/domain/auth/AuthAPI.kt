package com.igorj.core.domain.auth

interface AuthAPI {
    suspend fun attemptLogin(login: String, password: String): String
    fun register(login: String, password: String)
    fun changePasswordViaOldPassword(oldPassword: String, newPassword: String)
    fun changePasswordViaVerificationCode(codeFromEmail: String, newPassword: String)

    // TODO safe necessary data in encrypted shared preferences (token, credentials)
}