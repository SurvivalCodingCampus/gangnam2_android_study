package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredients(
    val amount: Int,
    val ingredient: Ingredient
)