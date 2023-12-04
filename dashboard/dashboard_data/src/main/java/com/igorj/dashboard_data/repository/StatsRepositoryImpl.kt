package com.igorj.dashboard_data.repository

import com.igorj.dashboard_domain.model.User
import com.igorj.dashboard_domain.model.UserStats
import com.igorj.dashboard_domain.repository.StatsRepository

class StatsRepositoryImpl: StatsRepository {
    override suspend fun getBestPeopleInGroup(groupId: Int, listSize: Int): Result<List<User>> {
        val fakeBestPeopleInGroupList = listOf(
            User(
                name = "John",
                flickers = 49,
                imageUrl = "https://i.imgur.com/36nMXsk.jpg",
                studentId = null,
                studentGroupId = null
            ),
            User(
                name = "Peter",
                flickers = 42,
                imageUrl = "https://i.imgur.com/E9D7cJT.jpg",
                studentId = 123456,
                studentGroupId = 3
            ),
            User(
                name = "Alice",
                flickers = 35,
                imageUrl = "https://i.imgur.com/5Ny1A0G.jpg",
                studentId = null,
                studentGroupId = null
            ),
            User(
                name = "Bob",
                flickers = 29,
                imageUrl = "https://i.imgur.com/ehbaBkE.jpg",
                studentId = null,
                studentGroupId = null
            ),
        )
        val result = fakeBestPeopleInGroupList.subList(0, listSize)
        return Result.success(result)
    }

    override suspend fun getUserStats(userId: Int): Result<UserStats> {
        val userStats = UserStats(
            questionSolved = 57,
            chaptersFinished = 5,
            averageAnswerTime = 8.2,
            mostAnswersInDay = 12,
            examBonus = 2,
            danteBonus = 1
        )
        return Result.success(userStats)
    }
}