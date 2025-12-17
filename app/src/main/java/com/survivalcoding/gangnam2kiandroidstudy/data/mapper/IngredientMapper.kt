package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient

fun IngredientDto.toModel(): Ingredient {
    return Ingredient(
        id = this.id ?: -1,
        name = this.name ?: "",
        image = this.image ?: ""
    )
}