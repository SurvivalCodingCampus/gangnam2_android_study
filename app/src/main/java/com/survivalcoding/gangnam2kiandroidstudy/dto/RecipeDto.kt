package com.survivalcoding.gangnam2kiandroidstudy.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    val id: Int,
    val category: String? = null,
    val name: String? = null,
    val image: String? = null,
    val chef: String? = null,
    val time: String? = null,
    val rating: Double? = null,
)
