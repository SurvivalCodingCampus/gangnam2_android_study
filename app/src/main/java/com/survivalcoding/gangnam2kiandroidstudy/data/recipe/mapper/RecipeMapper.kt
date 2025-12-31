package com.survivalcoding.gangnam2kiandroidstudy.data.recipe.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.dto.IngredientDTO
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.dto.RecipeDTO
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.dto.RecipeIngredientDTO
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.recipe.Recipe

fun RecipeDTO.toModel(): Recipe =
    Recipe(
        id = this.id?:0,
        category = this.category?:"",
        name = this.name?:"Unknown Title",
        imageUrl = this.image?:"",
        chef = this.chef?:"Unknown Chef",
        time = this.time ?:"",
        rating = this.rating ?: 0.0,
        createdAt = this.createdAt?: System.currentTimeMillis(),
        isSaved = this.isSaved,
        address = this.address?:"",
    )

fun IngredientDTO.toModel(): Ingredient? {
    val id = this.id ?: return null
    val name = this.name ?: return null
    val image = this.image ?: return null

    return Ingredient(
        id = id,
        name = name,
        image = image
    )
}

fun RecipeIngredientDTO.toModel(): RecipeIngredient? {
    val ingredient = this.ingredient?.toModel() ?: return null
    val amount = this.amount ?: return null

    return RecipeIngredient(
        id = ingredient.id,
        name = ingredient.name,
        amount = amount,
        image = ingredient.image
    )
}
