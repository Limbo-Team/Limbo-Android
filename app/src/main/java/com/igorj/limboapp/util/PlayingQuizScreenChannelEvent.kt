package com.igorj.limboapp.util

import com.igorj.limboapp.model.FinishedQuizResponse

sealed class PlayingQuizScreenChannelEvent {
    data class OnNavigateWithResponseFromServer(val response: FinishedQuizResponse?): PlayingQuizScreenChannelEvent()
}