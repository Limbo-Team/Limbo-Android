package com.igorj.limboapp.model

data class Chapter(
    val chapterId: String,
    val chapterTitle: String,
    val maximumQuizzes: Int,
    val doneQuizzes: Int,
    val percentage: Int
)
