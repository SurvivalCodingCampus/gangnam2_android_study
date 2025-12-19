package com.survivalcoding.gangnam2kiandroidstudy.domain.model

data class HomeRecipe(
    val recipe: Recipe,
    val chefImageUrl: String,
    val isBookmarked: Boolean
)