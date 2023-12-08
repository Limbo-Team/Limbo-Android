package com.igorj.quiz_presentation.finish_quiz

sealed class FinishQuizEvent {
    object OnFinish: FinishQuizEvent()
    object OnBackButtonClick: FinishQuizEvent()
}