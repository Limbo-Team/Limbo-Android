package com.igorj.limboapp.repository.implementation

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.igorj.limboapp.model.Chapter
import com.igorj.limboapp.model.FinishedQuizResponse
import com.igorj.limboapp.model.Question
import com.igorj.limboapp.model.Quiz
import com.igorj.limboapp.repository.interfaces.AuthAPI
import com.igorj.limboapp.repository.interfaces.ChaptersRepository
import com.igorj.limboapp.repository.interfaces.QuizApi
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume

class ChaptersRepositoryImpl(
    val authApi: AuthAPI,
    val quizApi: QuizApi,
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

    override suspend fun getChapterQuizzes(chapterId: String): Result<List<Quiz>> {
        return suspendCancellableCoroutine { continuation ->
            val queue = Volley.newRequestQueue(context)
            val url = "https://limbo-backend.onrender.com/user/chapters/$chapterId/quizzes"

            val stringRequest = object : StringRequest(
                Method.GET, url,
                Response.Listener { response ->
                    val gson = Gson()
                    val listType = object : TypeToken<List<Quiz>>() {}.type
                    val quizzes: List<Quiz> = gson.fromJson(response, listType)
                    continuation.resume(Result.success(quizzes))
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

    override suspend fun getQuizQuestions(quizId: String): Result<List<Question>> {
        return suspendCancellableCoroutine { continuation ->
            val queue = Volley.newRequestQueue(context)
            val url = "https://limbo-backend.onrender.com/user/quizzes/$quizId/questions"

            val stringRequest = object : StringRequest(
                Method.GET, url,
                Response.Listener { response ->
                    val gson = Gson()
                    val listType = object : TypeToken<List<Question>>() {}.type
                    val questions: List<Question> = gson.fromJson(response, listType)
                    continuation.resume(Result.success(questions))
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

    override suspend fun sendAnswers(quizId: String, answers: Map<String, String>): Result<FinishedQuizResponse> {
        val jsonArray = JSONArray()
        answers.forEach { (questionId, answer) ->
            val answerObj = JSONObject()
            answerObj.put("questionId", questionId)
            answerObj.put("answer", answer)
            jsonArray.put(answerObj)
        }

        val jsonString = jsonArray.toString()
        val requestBody = jsonString.toRequestBody("application/json".toMediaTypeOrNull())

        try {
            val response = quizApi.sendAnswers(quizId, requestBody)

            if (response.isSuccessful) {
                response.body()?.let { finishedQuizResponse ->
                    return Result.success(finishedQuizResponse)
                } ?: return Result.failure(Exception("Response body is null"))
            } else {
                return Result.failure(Exception("Error: ${response.code()} - ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

}