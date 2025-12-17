package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

fun RecipeDto.toModel(): Recipe {
    return Recipe(
        id = this.id ?: 0,
        title = this.name ?: "제목 없음",
        chef = this.chef ?: "Unknown Chef",
        time = this.time ?: "",
        category = this.category ?: "",
        rating = this.rating ?: 0.0,
        imageUrls = this.image ?: "",
        createdAt = this.createdAt ?: System.currentTimeMillis(),
        address = this.address ?: ""
    )
}