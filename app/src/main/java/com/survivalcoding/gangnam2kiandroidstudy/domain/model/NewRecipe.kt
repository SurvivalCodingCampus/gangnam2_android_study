package com.survivalcoding.gangnam2kiandroidstudy.domain.model

// Home 전용 Recipe + Chef
data class NewRecipe(
    val recipeId: Int,
    val title: String,
    val rating: Double,
    val time: String,
    val image: String,
    val chefName: String,
    val createdAt: Long,
    val chefProfileImageUrl: String?,
)
