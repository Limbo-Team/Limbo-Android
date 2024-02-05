package com.igorj.limboapp.model

data class UserStats(
    val chaptersDone: Int = 0,
    val quizzesDone: Int = 0,
    val userRewards: List<String> = listOf()
)