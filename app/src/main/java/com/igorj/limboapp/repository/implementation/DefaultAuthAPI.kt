package com.igorj.limboapp.repository.implementation

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.igorj.limboapp.model.LoginResponse
import com.igorj.limboapp.model.User
import com.igorj.limboapp.repository.interfaces.AuthAPI
import org.json.JSONObject
import javax.inject.Inject

class DefaultAuthAPI @Inject constructor(
    private val context: Context,
    private val authSharedPreferences: AuthSharedPreferences
): AuthAPI {
    companion object {
        const val TAG = "DefaultAuthAPI"
    }
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
            "${AuthAPI.BASE_URL}/user/signin",
            params,
            { response ->
                val loginResponse = Gson().fromJson(response.toString(), LoginResponse::class.java)
                saveToken(loginResponse.authToken)
                saveUserInfo(
                    User(
                        loginResponse.firstName,
                        loginResponse.lastName,
                        loginResponse.email,
                        loginResponse.image,
                        loginResponse.points
                    )
                )
                onResult(loginResponse.authToken)
            }, { error ->
                error.printStackTrace()
                onResult("")
            }
        )
        queue.add(jsonObjectRequest)
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        password: String,
        email: String,
        onResult: (Boolean) -> Unit
    ) {
        val queue = Volley.newRequestQueue(context)
        val params = JSONObject(
            mapOf(
                "firstName" to firstName,
                "lastName" to lastName,
                "password" to password,
                "email" to email
            )
        )
        val stringRequest = object : StringRequest(
            Method.POST,
            "${AuthAPI.BASE_URL}/user/signup",
            Response.Listener { _ ->
                onResult(true)
            },
            Response.ErrorListener { error ->
                error.networkResponse?.let {
                    Log.d(TAG, "Failed with status code ${it.statusCode}")
                } ?: Log.d(TAG, "Request failed.")
                onResult(false)
            }
        ) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return params.toString().toByteArray(Charsets.UTF_8)
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Accept"] = "application/json"
                return headers
            }
        }
        queue.add(stringRequest)
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

    override fun saveUserInfo(user: User) {
        authSharedPreferences.putString(AuthAPI.USER_INFO_KEY, Gson().toJson(user))
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

    override fun getUserInfo(): User {
        return Gson().fromJson(authSharedPreferences.getString(AuthAPI.USER_INFO_KEY), User::class.java)
    }

    override fun logout() {
        authSharedPreferences.empty()
    }
}