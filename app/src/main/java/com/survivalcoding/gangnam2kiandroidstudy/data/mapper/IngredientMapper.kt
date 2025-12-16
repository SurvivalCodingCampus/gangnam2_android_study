package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient

fun IngredientDto.toModel(): Ingredient {
    return Ingredient(
        id = id ?: 0,
        name = name ?: "",
        image = image ?: "",
        weight = weight ?: "",
        recipeId = recipeId ?: 0,
    )
}