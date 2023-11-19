package com.igorj.dashboard_presentation.chapters

import com.igorj.dashboard_domain.model.Chapter

data class ChaptersState(
    val chapters: List<Chapter> = emptyList(),
    val selectedScreen: String = "chapters",
    val flickers: Int = 0
)
