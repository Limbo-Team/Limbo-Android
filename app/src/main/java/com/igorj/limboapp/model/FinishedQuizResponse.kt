package com.igorj.limboapp.model

data class FinishedQuizResponse(
    val isCorrect: Boolean = false,
    val earnedPoints: Int = 0,
    val totalCorrectAnswers: Int = 0,
    val totalQuestions: Int = 0
)
