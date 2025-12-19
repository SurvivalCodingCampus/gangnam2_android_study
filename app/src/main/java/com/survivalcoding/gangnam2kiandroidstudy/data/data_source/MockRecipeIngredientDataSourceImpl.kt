package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeIngredientDto
import javax.inject.Inject

class MockRecipeIngredientDataSourceImpl @Inject constructor() : RecipeIngredientDataSource {
    private val recipeIngredients = listOf(
        RecipeIngredientDto(1, 3, 500),
        RecipeIngredientDto(1, 9, 50),
        RecipeIngredientDto(1, 8, 10),
        RecipeIngredientDto(1, 1, 100),

        RecipeIngredientDto(2, 6, 300),
        RecipeIngredientDto(2, 4, 200),
        RecipeIngredientDto(2, 8, 5),
        RecipeIngredientDto(2, 3, 500),

        RecipeIngredientDto(3, 6, 200),
        RecipeIngredientDto(3, 4, 150),
        RecipeIngredientDto(3, 1, 100),

        RecipeIngredientDto(5, 5, 150),
        RecipeIngredientDto(5, 8, 5),

        RecipeIngredientDto(6, 2, 500),
        RecipeIngredientDto(6, 9, 100),

        RecipeIngredientDto(7, 1, 200),

        RecipeIngredientDto(8, 6, 200),
        RecipeIngredientDto(8, 4, 100),

        RecipeIngredientDto(9, 9, 300),

        RecipeIngredientDto(10, 7, 100),

        RecipeIngredientDto(11, 6, 300),
        RecipeIngredientDto(11, 4, 300),
        RecipeIngredientDto(11, 8, 5),
        RecipeIngredientDto(11, 3, 500),

        RecipeIngredientDto(12, 6, 300),
        RecipeIngredientDto(12, 4, 400),
        RecipeIngredientDto(12, 8, 5),
        RecipeIngredientDto(12, 3, 500),
    )

    override suspend fun getIngredientsByRecipeId(
        recipeId: Int
    ): List<RecipeIngredientDto> {
        return recipeIngredients
            .filter { it.recipeId == recipeId }
    }
}
