package com.igorj.limboapp.model

data class UserStats(
    val questionSolved: Int = 0,
    val chaptersFinished: Int = 0,
    val averageAnswerTime: Double = 0.0,
    val mostAnswersInDay: Int = 0,
    val examBonus: Int = 0,
    val danteBonus: Int = 0
)