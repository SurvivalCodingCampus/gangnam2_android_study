package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.IngredientDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeIngredientDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.IngredientItemDto
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import javax.inject.Inject

class IngredientRepositoryImpl @Inject constructor(
    private val ingredientDataSource: IngredientDataSource,
    private val recipeIngredientDataSource: RecipeIngredientDataSource
) : IngredientRepository {

    override suspend fun getIngredientsByRecipeId(
        recipeId: Int
    ): List<IngredientItem> {

        // 아이디로 가져오기
        val recipeIngredients =
            recipeIngredientDataSource.getIngredientsByRecipeId(recipeId)

        // 전체 재료 조회
        val ingredients =
            ingredientDataSource.getIngredients()

        // 조합
        return recipeIngredients.map { mapping ->
            val ingredient = ingredients.firstOrNull {
                it.id == mapping.ingredientId
            }

            IngredientItemDto(
                id = ingredient?.id,
                name = ingredient?.name,
                image = ingredient?.image,
                amount = mapping.amount
            )
        }.map { it.toModel() }

    }
}
