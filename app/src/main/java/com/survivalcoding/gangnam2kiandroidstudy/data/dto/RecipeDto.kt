package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import com.survivalcoding.gangnam2kiandroidstudy.data.model.HomeImage
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    val id: Int? = null,
    val category: String? = null,
    val name: String? = null,
    val image: String? = null,
    val chef: String? = null,
    val time: String? = null,
    val rating: Double? = null,
    val createdAt: Long? = null,
    val homeImage: HomeImage? = null,
)
