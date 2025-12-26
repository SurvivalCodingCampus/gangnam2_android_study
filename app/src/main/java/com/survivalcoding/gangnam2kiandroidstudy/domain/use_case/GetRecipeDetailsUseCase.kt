package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredients
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipesRepository


class GetRecipeDetailsUseCase(
    private val recipesRepository: RecipesRepository,
    private val proceduresRepository: ProcedureRepository
) {
    suspend fun execute(id: Int): Pair<List<Ingredients>, List<Procedure>> {

        val recipe = recipesRepository.getSavedRecipes().find { it.id == id }
        return if (recipe != null) {
            Pair(
                recipe.ingredients,
                proceduresRepository.getProcedureByRecipeId(id)
            )
        } else {
            Pair(
                emptyList(),
                proceduresRepository.getProcedureByRecipeId(id)
            )
        }

    }
}