package com.survivalcoding.gangnam2kiandroidstudy.domain.model

data class User(
    val id: Long,
    val name: String,
    val savedRecipeIds: List<Long>,
)
