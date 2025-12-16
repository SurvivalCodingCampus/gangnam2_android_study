package com.survivalcoding.gangnam2kiandroidstudy.domain.model

data class Recipe(
    val id: Int,
    val title: String,
    val chef: String,
    val time: String,
    val category: String,
    val rating: Double,
    val imageUrls: String,
    val createdAt: Long,
)
