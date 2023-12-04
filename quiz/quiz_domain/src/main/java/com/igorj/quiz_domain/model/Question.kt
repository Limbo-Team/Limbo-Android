package com.igorj.quiz_domain.model

import androidx.annotation.DrawableRes

data class Question(
    val text: String,
    @DrawableRes val image: Int?,
    val wrongAnswers: ArrayList<String>,
    val correctAnswer: String
)
