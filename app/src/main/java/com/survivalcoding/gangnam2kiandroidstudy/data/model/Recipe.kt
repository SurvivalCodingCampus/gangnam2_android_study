package com.survivalcoding.gangnam2kiandroidstudy.data.model

data class Recipe(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val chef: String,
    val time: Int,
    val rating: Double,
)
