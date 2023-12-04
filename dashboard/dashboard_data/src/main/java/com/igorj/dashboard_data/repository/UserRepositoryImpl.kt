package com.igorj.dashboard_data.repository

import com.igorj.dashboard_domain.model.User
import com.igorj.dashboard_domain.repository.UserRepository

class UserRepositoryImpl: UserRepository {
    override suspend fun getUser(): Result<User> {
        return Result.success(User(
            "Igor",
            "Joński",
            "jonskiigor@gmail.com",
            "https://i.imgur.com/36nMXsk.jpg",
            50,
            240689,
            1
        ))
    }
}