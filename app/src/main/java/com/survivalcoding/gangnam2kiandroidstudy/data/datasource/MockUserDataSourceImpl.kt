package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.UserDto

class MockUserDataSourceImpl : UserDataSource {
    override suspend fun getUser(): UserDto {
        return UserDto(
            userId = 1,
            name = "John Doe",
            savedRecipesId = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        )
    }

}