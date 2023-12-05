package com.igorj.quiz_data.repository

import com.igorj.quiz_domain.model.Question
import com.igorj.quiz_domain.repository.QuestionsRepository

class QuestionsRepositoryImpl: QuestionsRepository {
    override suspend fun getQuestions(listSize: Int): Result<List<Question>> {
        val questions = listOf(
            Question(
                text = "Jaka jest poprawna odpowiedź?",
                imageUrl = "https://i.imgur.com/NuB7dev.png",
                wrongAnswers = arrayListOf("A", "B", "C"),
                correctAnswer = "D"
            ),
            Question(
                text = "Jaka jest poprawna odpowiedź?",
                imageUrl = "https://i.imgur.com/NuB7dev.png",
                wrongAnswers = arrayListOf("A", "B", "C"),
                correctAnswer = "D"
            ),
            Question(
                text = "Jaka jest poprawna odpowiedź?",
                imageUrl = "https://i.imgur.com/NuB7dev.png",
                wrongAnswers = arrayListOf("A", "B", "C"),
                correctAnswer = "D"
            ),
            Question(
                text = "Jaka jest poprawna odpowiedź?",
                imageUrl = "https://i.imgur.com/NuB7dev.png",
                wrongAnswers = arrayListOf("A", "B", "C"),
                correctAnswer = "D"
            ),
        )
        return Result.success(questions)
    }
}