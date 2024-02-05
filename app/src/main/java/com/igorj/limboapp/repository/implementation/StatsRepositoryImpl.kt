package com.igorj.limboapp.repository.implementation

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.igorj.limboapp.model.Chapter
import com.igorj.limboapp.model.User
import com.igorj.limboapp.model.UserStats
import com.igorj.limboapp.repository.interfaces.AuthAPI
import com.igorj.limboapp.repository.interfaces.StatsRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class StatsRepositoryImpl(
    val authApi: AuthAPI,
    val context: Context
): StatsRepository {
    override suspend fun getBestPeopleInGroup(groupId: Int, listSize: Int): Result<List<User>> {
        val fakeBestPeopleInGroupList = listOf(
            User(
                name = "John",
                flickers = 49,
                imageUrl = "https://i.imgur.com/36nMXsk.jpg",
                studentId = null,
                studentGroupId = null
            ),
            User(
                name = "Peter",
                flickers = 42,
                imageUrl = "https://i.imgur.com/E9D7cJT.jpg",
                studentId = 123456,
                studentGroupId = 3
            ),
            User(
                name = "Alice",
                flickers = 35,
                imageUrl = "https://i.imgur.com/5Ny1A0G.jpg",
                studentId = null,
                studentGroupId = null
            ),
            User(
                name = "Bob",
                flickers = 29,
                imageUrl = "https://i.imgur.com/ehbaBkE.jpg",
                studentId = null,
                studentGroupId = null
            ),
        )
        val result = fakeBestPeopleInGroupList.subList(0, listSize)
        return Result.success(result)
    }

    override suspend fun getUserStats(): Result<UserStats> {
        return suspendCancellableCoroutine { continuation ->
            val queue = Volley.newRequestQueue(context)
            val url = "https://limbo-backend.onrender.com/user/stats"

            val stringRequest = object : StringRequest(
                Method.GET, url,
                Response.Listener { response ->
                    val gson = Gson()
                    val userStatsType = object : TypeToken<UserStats>() {}.type
                    val userStats: UserStats = gson.fromJson(response, userStatsType)
                    continuation.resume(Result.success(userStats))
                },
                Response.ErrorListener { error ->
                    continuation.resume(Result.failure(error))
                }) {

                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    Log.d("StatsRepositoryImpl", "authApi.getToken(): ${authApi.getToken()}")
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