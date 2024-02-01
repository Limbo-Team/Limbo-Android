package com.igorj.limboapp.repository.interfaces

import com.igorj.limboapp.model.User

interface UserRepository {

    suspend fun getUser(): Result<User>
}