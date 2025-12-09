package com.survivalcoding.gangnam2kiandroidstudy.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeRootDto(
    @SerialName("recipes")
    val recipes: List<RecipeDto>? = listOf()
)