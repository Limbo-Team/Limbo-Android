package com.igorj.dashboard_domain.repository

import com.igorj.dashboard_domain.model.Person
import com.igorj.dashboard_domain.model.UserStats

interface StatsRepository {

    suspend fun getBestPeopleInGroup(
        groupId: Int,
        listSize: Int
    ): Result<List<Person>>

    suspend fun getUserStats(
        userId: Int
    ): Result<UserStats>
}