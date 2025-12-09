package com.survivalcoding.gangnam2kiandroidstudy.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeCard(
    val title: String,
    val chef: String,
    val time: String,
    val rating: Double,
    val imageUrls: String,
)
