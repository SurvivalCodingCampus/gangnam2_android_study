package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.UserDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.User

fun UserDto.toUser() = User(
    userId = userId ?: 0,
    name = name ?: "",
    savedRecipesId = savedRecipesId ?: emptyList()
)