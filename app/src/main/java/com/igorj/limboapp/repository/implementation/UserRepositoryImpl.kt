package com.igorj.limboapp.repository.implementation

import com.igorj.limboapp.model.User
import com.igorj.limboapp.repository.interfaces.UserRepository

class UserRepositoryImpl: UserRepository {
    override suspend fun getUser(): Result<User> {
        return Result.success(
            User(
            "Igor",
            "Joński",
            "jonskiigor@gmail.com",
            "https://i.imgur.com/36nMXsk.jpg",
            50,
            240689,
            1
        )
        )
    }
}