package com.igorj.quiz_domain.model

import androidx.annotation.DrawableRes

data class Question(
    val text: String,
    val imageUrl: String? = null,
    val wrongAnswers: ArrayList<String>,
    val correctAnswer: String
)
