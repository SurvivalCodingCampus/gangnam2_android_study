package com.survivalcoding.running.domain.repository

import com.survivalcoding.running.domain.domain.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}