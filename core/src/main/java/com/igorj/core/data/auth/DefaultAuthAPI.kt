package com.igorj.core.data.auth

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.igorj.core.domain.auth.AuthAPI
import org.json.JSONObject
import javax.inject.Inject

class DefaultAuthAPI @Inject constructor(
    private val context: Context,
    private val authSharedPreferences: AuthSharedPreferences
): AuthAPI {
    override suspend fun authorize(onResult: (String) -> Unit) {
        val queue = Volley.newRequestQueue(context)
        val params = JSONObject(
            mapOf(
                "email" to getUsername(),
                "password" to getPassword()
            )
        )
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            AuthAPI.BASE_URL,
            params,
            { response ->
                val token = response.getString("token")
                Log.d("LOGCAT", "token: $token")
                saveToken(token)
                onResult(token)
            }, { error ->
                Log.d("LOGCAT", "error: $error")
                onResult("")
            }
        )
        queue.add(jsonObjectRequest)
    }

    override fun saveUsername(username: String) {
        authSharedPreferences.putString(AuthAPI.USERNAME_KEY, username)
    }

    override fun savePassword(password: String) {
        authSharedPreferences.putString(AuthAPI.PASSWORD_KEY, password)
    }

    override fun saveToken(token: String) {
        authSharedPreferences.putString(AuthAPI.TOKEN_KEY, token)
    }

    override fun getUsername(): String {
        return authSharedPreferences.getString(AuthAPI.USERNAME_KEY) ?: ""
    }

    override fun getPassword(): String {
        return authSharedPreferences.getString(AuthAPI.PASSWORD_KEY) ?: ""
    }

    override fun getToken(): String {
        return authSharedPreferences.getString(AuthAPI.TOKEN_KEY) ?: ""
    }

    override fun logout() {
        authSharedPreferences.empty()
    }
}