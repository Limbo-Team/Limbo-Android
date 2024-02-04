package com.igorj.limboapp.screen.quizzes

import com.igorj.limboapp.model.Quiz

data class QuizzesState(
    val quizzes: List<Quiz> = emptyList(),
    val selectedScreen: String = "quizzes",
    val flickers: Int = 0
)
