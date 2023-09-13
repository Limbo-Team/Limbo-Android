package com.igorj.dashboard_domain.repository

import com.igorj.dashboard_domain.model.Person

interface StatsRepository {

    suspend fun getBestPeopleInGroup(
        groupId: Int,
        listSize: Int
    ): Result<List<Person>>

    suspend fun getUserStats(
        userId: Int
    )
}