package com.igorj.limboapp.repository.implementation

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.igorj.limboapp.model.Chapter
import com.igorj.limboapp.repository.interfaces.AuthAPI
import com.igorj.limboapp.repository.interfaces.ChaptersRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ChaptersRepositoryImpl(
    val authApi: AuthAPI,
    val context: Context
): ChaptersRepository {

    override suspend fun getChapters(): Result<List<Chapter>> {
        return suspendCancellableCoroutine { continuation ->
            val queue = Volley.newRequestQueue(context)
            val url = "https://limbo-backend.onrender.com/user/chapters"

            val stringRequest = object : StringRequest(
                Method.GET, url,
                Response.Listener { response ->
                    val gson = Gson()
                    val listType = object : TypeToken<List<Chapter>>() {}.type
                    val chapters: List<Chapter> = gson.fromJson(response, listType)
                    continuation.resume(Result.success(chapters))
                },
                Response.ErrorListener { error ->
                    continuation.resumeWithException(error)
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