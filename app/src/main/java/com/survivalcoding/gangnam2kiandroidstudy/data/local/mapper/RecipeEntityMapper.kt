package com.survivalcoding.gangnam2kiandroidstudy.data.local.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngridentDto
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientListDto
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.IngredientEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.RecipeEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.RecipeIngredientEntity

fun RecipeDto.toEntity(): RecipeEntity {
    return RecipeEntity(
        id = this.id ?: 0,
        name = this.name ?: "",
        image = this.image ?: "",
        category = this.category ?: "",
        chef = this.chef ?: "",
        time = this.time ?: "",
        rating = this.rating ?: 0.0,
        address = this.address ?: "",
        createdAt = this.createdAt ?: 0L
    )
}

fun RecipeEntity.toDto(ingredients: List<IngredientListDto>): RecipeDto {
    return RecipeDto(
        id = this.id,
        name = this.name,
        image = this.image,
        category = this.category,
        chef = this.chef,
        time = this.time,
        rating = this.rating,
        address = this.address,
        createdAt = this.createdAt,
        ingredients = ingredients
    )
}

fun IngredientEntity.toDto(): IngridentDto {
    return IngridentDto(
        id = this.id,
        name = this.name,
        image = this.image
    )
}

fun IngridentDto.toEntity(): IngredientEntity {
    return IngredientEntity(
        id = this.id ?: 0,
        name = this.name ?: "",
        image = this.image ?: ""
    )
}
