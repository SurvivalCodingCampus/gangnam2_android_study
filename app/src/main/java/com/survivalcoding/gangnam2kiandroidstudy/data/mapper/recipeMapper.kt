package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

fun RecipeDto.toModel(): Recipe {
    return Recipe(
        title = this.name ?: "제목 없음",
        chef = this.chef ?: "Unknown Chef",
        time = this.time ?: "",
        rating = this.rating ?: 0.0,
        imageUrls = this.image ?: ""
    )
}