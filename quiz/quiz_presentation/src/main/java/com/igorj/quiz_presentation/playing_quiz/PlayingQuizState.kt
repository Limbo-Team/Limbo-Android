package com.igorj.quiz_presentation.playing_quiz

import com.igorj.quiz_domain.model.Question

data class PlayingQuizState(
    val questions: List<Question> = listOf(),
    val currentQuestionIndex: Int = 0,
    val isFinished: Boolean = false,
    val answers: List<String> = listOf(),
    val timer: Int = 60,
)