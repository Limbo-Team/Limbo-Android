package com.igorj.limboapp.screen.chapters

import com.igorj.limboapp.model.Chapter

data class ChaptersState(
    val chapters: List<com.igorj.limboapp.model.Chapter> = emptyList(),
    val selectedScreen: String = "chapters",
    val flickers: Int = 0
)
