package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientDTO
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient

fun IngredientDTO.toModel(): Ingredient {
    return Ingredient(
        id = id ?: 0,
        name = name ?: EMPTY_STRING,
        imageUrl = imageUrl ?: EMPTY_STRING,
    )
}