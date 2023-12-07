package com.igorj.quiz_presentation.playing_quiz

sealed class PlayingQuizEvent {
    object OnStart: PlayingQuizEvent()
    data class OnAnswerClick(val position: Int): PlayingQuizEvent()
    object OnNextQuestionClick: PlayingQuizEvent()
    object OnFinish: PlayingQuizEvent()
    object OnTimeTick: PlayingQuizEvent()
}