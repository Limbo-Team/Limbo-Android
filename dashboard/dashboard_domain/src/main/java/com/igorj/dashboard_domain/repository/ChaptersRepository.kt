package com.igorj.dashboard_domain.repository

import com.igorj.dashboard_domain.model.Chapter

interface ChaptersRepository {

    suspend fun getChapters(listSize: Int): Result<List<Chapter>>
}