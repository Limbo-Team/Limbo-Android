package com.igorj.limboapp.screen.stats

sealed class StatsEvent {
    object OnQuestionsSolvedClick : StatsEvent()
    object OnChaptersFinishedClick : StatsEvent()
    object OnAverageAnswerTimeClick : StatsEvent()
    object OnMostAnswersInDayClick : StatsEvent()
    object OnExamBonusClick : StatsEvent()
    object OnDanteBonusClick : StatsEvent()
    data class OnBottomNavBarClick(val route: String) : StatsEvent()
    object OnFlickersClick : StatsEvent()
    object OnProfileClick : StatsEvent()
}