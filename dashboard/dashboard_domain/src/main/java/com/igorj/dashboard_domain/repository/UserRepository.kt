package com.igorj.dashboard_domain.repository

import com.igorj.dashboard_domain.model.User

interface UserRepository {

    suspend fun getUser(): Result<User>
}