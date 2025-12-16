package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val dataSource: RecipeDataSource
) : RecipeRepository {

    override suspend fun findRecipe(id: Long): Recipe {
        val dto = dataSource.getRecipe(id)
            ?: throw NoSuchElementException("Recipe($id) not found")

        return dto.toModel()
    }

    override suspend fun findRecipes(): List<Recipe> {
        return dataSource.getRecipes()
            ?.filterNotNull()
            ?.map { it.toModel() }
            ?: emptyList()
    }

}

