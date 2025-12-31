package com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository

class IngredientRepositoryImpl(
    private val dataSource: RecipeDataSource
) : IngredientRepository {

    override suspend fun getIngredients(recipeId: Int): List<RecipeIngredient> {
        val recipe = dataSource.getRecipes()
            .firstOrNull { it.id == recipeId }

        return recipe?.ingredients.orEmpty()
            .mapNotNull { it.toModel() }
    }
}
