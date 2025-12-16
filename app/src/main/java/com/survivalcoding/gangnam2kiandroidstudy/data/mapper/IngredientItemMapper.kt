package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientItemDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientItem

fun IngredientItemDto.toModel(): IngredientItem {
    return IngredientItem(
        id = this.id ?: -1,
        name = this.name ?: "UNKNOWN",
        image = this.image ?: "",
        amount = this.amount ?: 0
    )
}