package com.igorj.quiz_presentation.playing_quiz

sealed class PlayingQuizEvent {
    object OnStart: PlayingQuizEvent()
    object OnNextQuestion: PlayingQuizEvent()
    object OnFinish: PlayingQuizEvent()
    data class OnAnswer(val answer: String): PlayingQuizEvent()
}