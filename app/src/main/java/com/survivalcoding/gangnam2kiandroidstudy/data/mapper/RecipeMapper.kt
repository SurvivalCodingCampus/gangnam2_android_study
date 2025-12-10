package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

fun RecipeDto.toModel(): Recipe {
    return Recipe(
        id = id ?: 0,
        category = category ?: "",
        name = name ?: "",
        image = image ?: "",
        chef = chef ?: "",
        time = time ?: "",
        rating = rating ?: 0.0
    )
}