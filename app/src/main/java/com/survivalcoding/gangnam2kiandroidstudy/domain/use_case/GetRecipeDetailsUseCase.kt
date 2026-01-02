package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredients
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipesRepository


class GetRecipeDetailsUseCase(
    private val recipesRepository: RecipesRepository,
    private val proceduresRepository: ProcedureRepository
) {
    suspend fun execute(id: Int): Triple<List<Ingredients>, List<Procedure>, Recipe> {

        val recipe = recipesRepository.getAllRecipes().find { it.id == id }
        return if (recipe != null) {
            Triple(
                recipe.ingredients,
                proceduresRepository.getProcedureByRecipeId(id),
                recipe
            )
        } else {
            Triple(
                emptyList(),
                proceduresRepository.getProcedureByRecipeId(id),
                Recipe(
                    category = "",
                    chef = "",
                    id = 0,
                    image = "",
                    ingredients = emptyList(),
                    name = "",
                    rating = 0.0,
                    time = "",
                    false
                )
            )
        }

    }
}