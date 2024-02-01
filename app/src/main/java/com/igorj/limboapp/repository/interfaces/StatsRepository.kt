package com.igorj.limboapp.repository.interfaces

import com.igorj.limboapp.model.User
import com.igorj.limboapp.model.UserStats

interface StatsRepository {

    suspend fun getBestPeopleInGroup(
        groupId: Int,
        listSize: Int
    ): Result<List<com.igorj.limboapp.model.User>>

    suspend fun getUserStats(
        userId: Int
    ): Result<com.igorj.limboapp.model.UserStats>
}