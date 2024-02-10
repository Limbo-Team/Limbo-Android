package com.igorj.limboapp.repository.interfaces

import com.igorj.limboapp.model.Chapter
import com.igorj.limboapp.model.Question
import com.igorj.limboapp.model.Quiz

interface ChaptersRepository {

    suspend fun getChapters(): Result<List<Chapter>>
    suspend fun getChapterQuizzes(chapterId: String): Result<List<Quiz>>
    suspend fun getQuizQuestions(quizId: String): Result<List<Question>>
    suspend fun sendAnswers(quizId: String, answers: Map<String, String>)
}