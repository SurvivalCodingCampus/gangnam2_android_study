package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.UserDto

interface UserDataSource {
    suspend fun getUser(): UserDto
}