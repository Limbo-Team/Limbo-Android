package com.igorj.limboapp.screen.playing_quiz

import com.igorj.limboapp.model.Question

data class PlayingQuizState(
    val chapterId: Int = 0,
    val questions: List<Question> = listOf(),
    val currentQuestionIndex: Int = 0,
    val isFinished: Boolean = false,
    val selectedAnswerPosition: Int = -1,
    val answers: List<String> = listOf(),
    val timeLeft: Float = 10f,
    val maxTime: Int = 10,
    val isLoading: Boolean = false,
    val answersChosenByUser: Map<String, String> = mapOf(),
)