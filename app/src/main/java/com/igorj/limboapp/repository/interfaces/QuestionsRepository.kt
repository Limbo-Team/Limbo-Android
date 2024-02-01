package com.igorj.limboapp.repository.interfaces

import com.igorj.limboapp.model.Question

interface QuestionsRepository {
        suspend fun getQuestions(
            chapterId: Int,
            listSize: Int
        ): Result<List<com.igorj.limboapp.model.Question>>
}