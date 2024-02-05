package com.igorj.limboapp.screen.stats

import com.igorj.limboapp.model.UserStats

data class StatsState(
    val selectedScreen: String = "stats",
    val userStats: UserStats = UserStats(),
    val flickers: Int = 0,
    val isLoading: Boolean = false
)