package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecipesWrapperDTO(
    val recipes: List<RecipeDTO?>? = null
)
