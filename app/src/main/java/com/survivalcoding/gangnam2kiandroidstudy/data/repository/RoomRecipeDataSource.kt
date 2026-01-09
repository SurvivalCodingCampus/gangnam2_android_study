package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientListDto
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.data.local.dao.RecipeDao
import com.survivalcoding.gangnam2kiandroidstudy.data.local.mapper.toDto
import com.survivalcoding.gangnam2kiandroidstudy.data.local.mapper.toEntity

class RoomRecipeDataSource(
    private val recipeDao: RecipeDao
) : RecipeDataSource {

    override suspend fun getRecipes(): List<RecipeDto> {
        val recipeEntities = recipeDao.getAllRecipes()
        return recipeEntities.map { recipeEntity ->
            val recipeIngredients = recipeDao.getRecipeIngredients(recipeEntity.id)
            val ingredientIds = recipeIngredients.map { it.ingredientId }
            val ingredients = recipeDao.getIngredientsByIds(ingredientIds)
            
            // Map back to IngredientListDto (combining Ingredient with Amount)
            val ingredientListDtos = recipeIngredients.mapNotNull { join ->
                val ingredient = ingredients.find { it.id == join.ingredientId }
                ingredient?.let {
                    IngredientListDto(
                        amount = join.amount,
                        ingredient = it.toDto()
                    )
                }
            }
            
            recipeEntity.toDto(ingredientListDtos)
        }
    }
}
