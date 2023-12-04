package com.igorj.dashboard_domain.repository

import com.igorj.dashboard_domain.model.User
import com.igorj.dashboard_domain.model.UserStats

interface StatsRepository {

    suspend fun getBestPeopleInGroup(
        groupId: Int,
        listSize: Int
    ): Result<List<User>>

    suspend fun getUserStats(
        userId: Int
    ): Result<UserStats>
}