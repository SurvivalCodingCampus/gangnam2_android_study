package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientListDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingrident

fun IngredientListDto.toModel(): Ingrident? {
    val ingredientDto = ingredient ?: return null

    return Ingrident(
        id = ingredientDto.id ?: return null,
        name = ingredientDto.name.orEmpty(),
        image = ingredientDto.image.orEmpty(),
        amount = amount ?: 0
    )
}
