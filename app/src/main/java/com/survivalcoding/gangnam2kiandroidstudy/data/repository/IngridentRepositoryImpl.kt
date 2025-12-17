package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingrident
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngridentRepository

class IngridentRepositoryImpl(
    private val recipeDataSource: MockRecipeDataSourceImpl
) : IngridentRepository {

    override suspend fun getIngrident(recipeId: Int): List<Ingrident> {
        return recipeDataSource.getRecipes()
            .firstOrNull { it.id == recipeId }
            ?.ingredients
            ?.mapNotNull { it.toModel() }
            ?: emptyList()
    }

}