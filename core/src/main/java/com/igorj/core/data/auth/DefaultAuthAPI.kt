package com.igorj.core.data.auth

import com.igorj.core.domain.auth.AuthAPI
import kotlinx.coroutines.delay

class DefaultAuthAPI: AuthAPI {
    override suspend fun attemptLogin(login: String, password: String): String {
        delay(2000)
        if (login == "test" && password == "pass") {
            return "token"
        }
        return ""
    }

    override fun register(login: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun changePasswordViaOldPassword(oldPassword: String, newPassword: String) {
        TODO("Not yet implemented")
    }

    override fun changePasswordViaVerificationCode(codeFromEmail: String, newPassword: String) {
        TODO("Not yet implemented")
    }
}