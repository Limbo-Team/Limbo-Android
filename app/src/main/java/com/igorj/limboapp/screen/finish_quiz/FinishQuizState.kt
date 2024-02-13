package com.igorj.limboapp.screen.finish_quiz

import com.igorj.limboapp.model.FinishedQuizResponse

data class FinishQuizState(
    val isLoading: Boolean = true,
    val finishedQuizResponse: FinishedQuizResponse? = null,
)
