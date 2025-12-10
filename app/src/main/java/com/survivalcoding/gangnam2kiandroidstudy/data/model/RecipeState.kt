package com.survivalcoding.gangnam2kiandroidstudy.data.model

data class RecipeState(
    val data: List<Recipe> = emptyList(),
    val error: String? = null,
)