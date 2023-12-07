package com.igorj.quiz_presentation.playing_quiz

sealed class PlayingQuizEvent {
    object OnStart: PlayingQuizEvent()
    data class OnAnswerClick(val position: Int): PlayingQuizEvent()
    data class OnNextQuestionClick(val answer: String): PlayingQuizEvent()
    object OnFinish: PlayingQuizEvent()
    object OnTimeTick: PlayingQuizEvent()
}