package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

fun RecipeDto.toModel(): Recipe {
    return Recipe(
        id = id ?: 0,
        category = category ?: "",
        name = name ?: "",
        image = image ?: "",
        chef = chef ?: "",
        time = time ?: "",
        rating = rating ?: 0.0,
        ingridents = ingredients?.filter { it.id != null }
            ?.map { it.toModel() }
            ?: emptyList(),
        procedures = procedures?.filter { it.recipeId != null }
            ?.map { it.toModel() }
            ?: emptyList()
    )
}