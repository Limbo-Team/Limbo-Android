package com.igorj.quiz_domain.repository

import com.igorj.quiz_domain.model.Question

interface QuestionsRepository {
        suspend fun getQuestions(
            chapterId: Int,
            listSize: Int
        ): Result<List<Question>>
}