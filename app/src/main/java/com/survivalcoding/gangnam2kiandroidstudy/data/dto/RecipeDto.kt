package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    val category: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val image: String? = "",
    val chef: String? = "",
    val time: String? = "",
    val rating: Double? = null
)

@Serializable
data class RecipeResponse(val recipes: List<RecipeDto>? = null)