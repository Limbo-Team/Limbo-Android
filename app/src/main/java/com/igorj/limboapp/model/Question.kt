package com.igorj.limboapp.model


data class Question(
    val questionId: String,
    val description: String,
    val answers: List<String>,
)
