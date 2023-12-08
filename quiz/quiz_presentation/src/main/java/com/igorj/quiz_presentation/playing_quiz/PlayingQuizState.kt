package com.igorj.quiz_presentation.playing_quiz

import com.igorj.quiz_domain.model.Question

data class PlayingQuizState(
    val chapterId: Int = 0,
    val questions: List<Question> = listOf(),
    val currentQuestionIndex: Int = 0,
    val isFinished: Boolean = false,
    val selectedAnswerPosition: Int = -1,
    val answers: List<String> = listOf(),
    val timeLeft: Float = 10f,
    val maxTime: Int = 10,
)