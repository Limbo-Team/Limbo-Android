package com.igorj.limboapp.screen.home

import com.igorj.limboapp.model.Chapter
import com.igorj.limboapp.model.User

data class HomeState(
    val bestPeople: List<User> = emptyList(),
    val miniChapters: List<Chapter> = emptyList(),
    val selectedScreen: String = "home",
    val flickers: Int = 0
)