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
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetRecipeDetailsUseCase(
    private val recipeRepository: RecipeRepository,
    private val chefRepository: ChefRepository,
    private val ingredientRepository: IngredientRepository,
    private val procedureRepository: ProcedureRepository
) {
    suspend operator fun invoke(recipeId: Int): Result<Triple<Pair<Recipe, Chef>, List<RecipeIngredient>, List<Procedure>>> {
        return suspendRunCatching {
            coroutineScope {
                val recipeDeferred = async {
                    recipeRepository.getRecipeById(recipeId)
                        ?: throw IllegalStateException("recipe가 없습니다.")
                }
                val ingredientDeferred = async {
                    ingredientRepository.getIngredientByRecipeId(recipeId)
                }
                val procedureDeferred = async {
                    procedureRepository.getProcedureByRecipeId(recipeId)
                }
                val recipe = recipeDeferred.await()
                val chefDeferred = async {
                    chefRepository.getChefByName(recipe.chef)
                }
                Triple(
                    recipe to chefDeferred.await(),
                    ingredientDeferred.await(),
                    procedureDeferred.await()
                )
            }
        }
    }
}