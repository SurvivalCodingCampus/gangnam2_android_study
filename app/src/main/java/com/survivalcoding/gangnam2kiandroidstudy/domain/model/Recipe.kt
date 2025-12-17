package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Recipe(
    val category: String,
    val chef: String,
    val id: Int,
    val image: String,
    val ingredients: List<Ingredients>,
    val name: String,
    val rating: Double,
    val time: String
)
