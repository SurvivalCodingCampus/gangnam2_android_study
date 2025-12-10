package com.survivalcoding.gangnam2kiandroidstudy.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    val recipes: List<Recipe>
)