package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeDetail
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class GetRecipeDetailUseCase(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository,
    private val chefRepository: ChefRepository,
    private val procedureRepository: ProcedureRepository,
) {
    suspend fun execute(recipeId: Int): RecipeDetail? {
        val recipe = recipeRepository.getRecipeById(recipeId) ?: return null
        val ingredients = ingredientRepository.getIngredientsByRecipeId(recipeId)
        val chef = chefRepository.getChefById(recipe.chefId)
        val procedures = procedureRepository.getProceduresByRecipeId(recipeId)

        return RecipeDetail(
            recipe = recipe,
            chef = chef,
            ingredients = ingredients,
            procedures = procedures,
        )
    }
}
