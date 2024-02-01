package com.igorj.limboapp.repository.implementation

import com.igorj.limboapp.model.Chapter
import com.igorj.limboapp.repository.interfaces.ChaptersRepository

class ChaptersRepositoryImpl: ChaptersRepository {

    override suspend fun getChapters(listSize: Int): Result<List<Chapter>> {
        val chaptersList = listOf(
            Chapter(
                id = 1,
                title = "Data operations",
                isUnlocked = true,
                isCompleted = true,
                maxPoints = 43,
                gainedPoints = 43
            ),
            Chapter(
                id = 2,
                title = "Manual memory management",
                isUnlocked = true,
                isCompleted = false,
                maxPoints = 23,
                gainedPoints = 12
            ),
            Chapter(
                id = 3,
                title = "Structure and union",
                isUnlocked = true,
                isCompleted = false,
                maxPoints = 54,
                gainedPoints = 46
            ),
            Chapter(
                id = 4,
                title = "Bit operations and preprocessor directives",
                isUnlocked = false,
                isCompleted = false,
                maxPoints = 32,
                gainedPoints = 0
            ),
            Chapter(
                id = 5,
                title = "Pointers and arrays",
                isUnlocked = false,
                isCompleted = false,
                maxPoints = 65,
                gainedPoints = 0
            ),
            Chapter(
                id = 6,
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