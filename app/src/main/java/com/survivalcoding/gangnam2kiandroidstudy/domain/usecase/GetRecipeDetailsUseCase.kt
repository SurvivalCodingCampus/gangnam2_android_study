package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.util.suspendRunCatching
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class GetRecipeDetailsUseCase(
    private val recipeRepository: RecipeRepository,
    private val chefRepository: ChefRepository,
    private val ingredientRepository: IngredientRepository,
    private val procedureRepository: ProcedureRepository
) {
    suspend operator fun invoke(recipeId: Int): Result<Triple<Pair<Recipe, Chef>, List<RecipeIngredient>, List<Procedure>>> {
        return suspendRunCatching {
            val recipe = recipeRepository.getRecipeById(recipeId)
            val ingredient = ingredientRepository.getIngredientByRecipeId(recipeId)
            val procedure = procedureRepository.getProcedureByRecipeId(recipeId)
            val chef = chefRepository.getChefByName(recipe?.name ?: throw Exception("recipe가 없습니다."))
            Triple(recipe to chef, ingredient, procedure)
        }
    }
}