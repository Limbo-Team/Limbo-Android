package com.igorj.dashboard_domain.model

data class Person(
    val name: String,
    val flickers: Int,
    val imageUrl: String,
    val studentId: Int?,
    val studentGroupId: Int?
)