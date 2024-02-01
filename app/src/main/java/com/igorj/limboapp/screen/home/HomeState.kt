package com.igorj.limboapp.screen.home

import com.igorj.limboapp.model.Chapter
import com.igorj.limboapp.model.User

data class HomeState(
    val bestPeople: List<com.igorj.limboapp.model.User> = emptyList(),
    val miniChapters: List<com.igorj.limboapp.model.Chapter> = emptyList(),
    val selectedScreen: String = "home",
    val flickers: Int = 0
)