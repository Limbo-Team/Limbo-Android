package com.igorj.limboapp.repository.interfaces

import com.igorj.limboapp.model.FinishedQuizResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface QuizApi {
    @Headers("Content-Type: application/json")

    @POST("/user/quizzes/{quizId}/answer")
    suspend fun sendAnswers(@Path("quizId") quizId: String, @Body answers: RequestBody): Response<FinishedQuizResponse>
}