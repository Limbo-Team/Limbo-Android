package com.igorj.dashboard_presentation.home

import com.igorj.dashboard_domain.model.Chapter
import com.igorj.dashboard_domain.model.User

data class HomeState(
    val bestPeople: List<User> = emptyList(),
    val miniChapters: List<Chapter> = emptyList(),
    val selectedScreen: String = "home",
    val flickers: Int = 0
)