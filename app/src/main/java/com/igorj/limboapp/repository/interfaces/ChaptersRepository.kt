package com.igorj.limboapp.repository.interfaces

import com.igorj.limboapp.model.Chapter

interface ChaptersRepository {

    suspend fun getChapters(listSize: Int): Result<List<Chapter>>
}