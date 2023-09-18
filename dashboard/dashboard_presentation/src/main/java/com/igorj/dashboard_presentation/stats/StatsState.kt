package com.igorj.dashboard_presentation.stats

import com.igorj.dashboard_domain.model.UserStats

data class StatsState(
    val selectedScreen: String = "stats",
    val userStats: UserStats = UserStats(),
    val flickers: Int = 0
)