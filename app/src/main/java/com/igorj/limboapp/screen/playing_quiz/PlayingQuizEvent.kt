package com.igorj.limboapp.screen.playing_quiz

sealed class PlayingQuizEvent {
    object OnStart: PlayingQuizEvent()
    data class OnAnswerClick(val position: Int): PlayingQuizEvent()
    data class OnNextQuestionClick(val answer: String): PlayingQuizEvent()
    data class OnFinish(val lastAnswer: String, val quizId: String): PlayingQuizEvent()
    data class OnTimeTick(val quizId: String): PlayingQuizEvent()
    object OnBackButtonClick: PlayingQuizEvent()
}