package com.survivalcoding.gangnam2kiandroidstudy.mapper

import com.survivalcoding.gangnam2kiandroidstudy.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.model.RecipeCard

fun RecipeDto.toModel(): RecipeCard {
    return RecipeCard(
        title = this.name ?: "",
        chef = this.chef ?: "Unknown",
        time = this.time ?: "-",
        rating = this.rating ?: 0.0,
        imageUrls = this.image ?: ""
    )
}