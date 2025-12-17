package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.HomeImage
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

fun RecipeDto.toModel(): Recipe {
    return Recipe(
        id = this.id ?: -1,
        title = this.name ?: "",
        chefId = this.chefId ?: -1,
        chefName = chefName ?: "UNKNOWN",
        time = this.time ?: "-",
        rating = this.rating ?: 0.0,
        imageUrls = this.image ?: "",
        createdAt = this.createdAt ?: 0L,
        category = this.category ?: "",
        homeImage = this.homeImage ?: HomeImage.FOOD1,
    )
}