package com.igorj.limboapp.screen.quizzes

sealed class QuizzesEvent {
    data class OnQuizClick(val quizId: Int): QuizzesEvent()
    data class OnQuizLongPress(val quizId: Int): QuizzesEvent()
    data class OnBottomNavBarClick(val route: String): QuizzesEvent()
    object OnFlickersClick: QuizzesEvent()
    object OnProfileClick: QuizzesEvent()
}