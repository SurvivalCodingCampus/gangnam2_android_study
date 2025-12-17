package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Int,
    val title: String,
    val chefId: Int,
    val chefName: String,
    val time: String,
    val rating: Double,
    val imageUrls: String,
    val createdAt: Long,
    val category: String,
    val homeImage: HomeImage,
)
