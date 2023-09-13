package com.igorj.dashboard_data.repository

import com.igorj.dashboard_domain.model.Chapter
import com.igorj.dashboard_domain.repository.ChaptersRepository

class ChaptersRepositoryImpl: ChaptersRepository {

    override suspend fun getChapters(listSize: Int): Result<List<Chapter>> {
        val chaptersList = listOf(
            Chapter(
                title = "Data operations",
                isUnlocked = true,
                isCompleted = true,
                maxPoints = 43,
                gainedPoints = 43
            ),
            Chapter(
                title = "Manual memory management",
                isUnlocked = true,
                isCompleted = false,
                maxPoints = 23,
                gainedPoints = 12
            ),
            Chapter(
                title = "Structure and union",
                isUnlocked = true,
                isCompleted = false,
                maxPoints = 54,
                gainedPoints = 46
            ),
            Chapter(
                title = "Bit operations and preprocessor directives",
                isUnlocked = false,
                isCompleted = false,
                maxPoints = 32,
                gainedPoints = 0
            ),
            Chapter(
                title = "Pointers and arrays",
                isUnlocked = false,
                isCompleted = false,
                maxPoints = 65,
                gainedPoints = 0
            ),
            Chapter(
                title = "Flow of control",
                isUnlocked = false,
                isCompleted = false,
                maxPoints = 54,
                gainedPoints = 0
            ),
        )
        return Result.success(chaptersList)
    }
}