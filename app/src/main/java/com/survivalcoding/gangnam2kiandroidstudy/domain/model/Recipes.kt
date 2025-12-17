package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Recipes(
    val recipes: List<Recipe>
)