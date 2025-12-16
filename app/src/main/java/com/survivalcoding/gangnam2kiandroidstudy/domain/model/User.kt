package com.survivalcoding.gangnam2kiandroidstudy.domain.model

data class User(
    val userId: Int,
    val name: String,
    val savedRecipesId: List<Int>,
)