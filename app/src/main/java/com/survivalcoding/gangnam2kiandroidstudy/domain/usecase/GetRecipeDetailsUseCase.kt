package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeDetail
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngridentRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class GetRecipeDetailsUseCase(
    private val recipeRepository: RecipeRepository,
    private val procedureRepository: ProcedureRepository,
    private val ingridentRepository: IngridentRepository,
) {
    suspend fun execute(recipeId: Int): RecipeDetail {
        val recipe = recipeRepository.getRecipes().filter { it.id == recipeId }[0]
        val procedures = procedureRepository.getProcedure(recipeId)
        val ingredients = ingridentRepository.getIngrident(recipeId)

        return RecipeDetail(
            recipe = recipe,
            procedures = procedures,
            ingredients = ingredients
        )
    }
}