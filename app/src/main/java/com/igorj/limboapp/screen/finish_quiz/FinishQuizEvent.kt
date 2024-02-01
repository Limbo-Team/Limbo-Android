package com.igorj.limboapp.screen.finish_quiz

sealed class FinishQuizEvent {
    object OnFinish: FinishQuizEvent()
    object OnBackButtonClick: FinishQuizEvent()
}