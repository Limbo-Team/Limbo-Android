package com.igorj.limboapp.repository.implementation

import com.igorj.limboapp.model.Question
import com.igorj.limboapp.repository.interfaces.QuestionsRepository

class QuestionsRepositoryImpl: QuestionsRepository {
    override suspend fun getQuestions(chapterId: Int, listSize: Int): Result<List<Question>> {
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
                wrongAnswers = arrayListOf("F", "G", "H"),
                correctAnswer = "X"
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