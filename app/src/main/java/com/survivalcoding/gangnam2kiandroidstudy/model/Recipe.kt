package com.survivalcoding.gangnam2kiandroidstudy.model

import kotlinx.serialization.Serializable


@Serializable
data class Recipe(
    val category: String,
    val chef: String,
    val id: Int,
    val image: String,
    val name: String,
    val rating: Double,
    val time: String
)