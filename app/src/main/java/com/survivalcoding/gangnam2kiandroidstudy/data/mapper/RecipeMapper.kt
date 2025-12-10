package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeCard

fun RecipeDto.toModel(): RecipeCard {
    return RecipeCard(
        title = this.name ?: "",
        chef = this.chef ?: "Unknown",
        time = this.time ?: "-",
        rating = this.rating ?: 0.0,
        imageUrls = this.image ?: ""
    )
}