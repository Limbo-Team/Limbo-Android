package com.igorj.dashboard_presentation.home

import com.igorj.dashboard_domain.model.Chapter
import com.igorj.dashboard_domain.model.Person

data class HomeState(
    val bestPeopleList: List<Person> = emptyList(),
    val miniChaptersList: List<Chapter> = emptyList(),
    val selectedScreen: String = "home",
    val flickers: Int = 0
)