package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class RecipeDto(
    val category: String? = null,
    val chef: String? = null,
    val id: Int? = 0,
    val image: String? = null,
    val ingredients: List<Ingredients>? = null,
    val name: String? = null,
    val rating: Double? = 0.0,
    val time: String? = null
)
