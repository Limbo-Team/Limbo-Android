package com.igorj.dashboard_domain.model

data class Chapter(
    val id: Int,
    val title: String,
    val isUnlocked: Boolean,
    val isCompleted: Boolean,
    val maxPoints: Int,
    val gainedPoints: Int
)
