package com.igorj.limboapp.repository.implementation

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.igorj.limboapp.model.User
import com.igorj.limboapp.model.UserStats
import com.igorj.limboapp.repository.interfaces.AuthAPI
import com.igorj.limboapp.repository.interfaces.UserRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class UserRepositoryImpl(
    val authApi: AuthAPI,
    val context: Context
): UserRepository {
    override suspend fun getUser(): Result<User> {
        return suspendCancellableCoroutine { continuation ->
            val queue = Volley.newRequestQueue(context)
            val url = "https://limbo-backend.onrender.com/user/info"

            val stringRequest = object : StringRequest(
                Method.GET, url,
                Response.Listener { response ->
                    val gson = Gson()
                    val useInfoType = object : TypeToken<User>() {}.type
                    val userInfo: User = gson.fromJson(response, useInfoType)
                    continuation.resume(Result.success(userInfo))
                },
                Response.ErrorListener { error ->
                    continuation.resume(Result.failure(error))
                }) {

                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Authorization"] = "Bearer ${authApi.getToken()}"
                    return headers
                }
            }

            continuation.invokeOnCancellation {
                stringRequest.cancel()
            }

            queue.add(stringRequest)
        }
    }
}