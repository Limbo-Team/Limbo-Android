package com.igorj.limboapp.screen.stats

import com.igorj.limboapp.model.UserStats

data class StatsState(
    val selectedScreen: String = "stats",
    val userStats: com.igorj.limboapp.model.UserStats = com.igorj.limboapp.model.UserStats(),
    val flickers: Int = 0
)