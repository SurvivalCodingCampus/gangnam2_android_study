package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val dataSource: RecipeDataSource
) : RecipeRepository {
    override suspend fun findRecipe(id: Long): Result<Recipe, String> {
        try {
            val response = dataSource.getRecipe(id)
            if (response != null && response.id != 0L) {
                return Result.Success(dataSource.getRecipe(id)!!.toModel())
            } else {
                return Result.Error("error : findRecipe($id) 실패")
            }

        } catch (e: Exception) {
            return Result.Error("error : findRecipe($id) 실패")
        }
    }

    override suspend fun findRecipes(): Result<List<Recipe>, String> {
        try {
            val response = dataSource.getRecipes()
            if (response != null) {
                return Result.Success(
                    response.filterNotNull()
                        .filter { it.id != 0L }
                        .map { it.toModel() }
                )

            } else {
                return Result.Error("error : findRecipes() 실패")
            }
        } catch (e: Exception) {
            return Result.Error("error : findRecipes() 실패")
        }
    }

}

