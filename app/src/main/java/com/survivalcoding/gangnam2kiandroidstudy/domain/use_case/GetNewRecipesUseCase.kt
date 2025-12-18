package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NewRecipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import javax.inject.Inject

class GetNewRecipesUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val chefRepository: ChefRepository,
) {
    suspend fun execute(): List<NewRecipe> {
        val oneMonthAgo = System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000

        return recipeRepository.getRecipes()
            .filter { it.createdAt >= oneMonthAgo }
            .sortedByDescending { it.createdAt }
            .map { recipe ->
                val chef = chefRepository.getChefById(recipe.chefId)

                NewRecipe(
                    recipeId = recipe.id,
                    title = recipe.title,
                    rating = recipe.rating,
                    time = recipe.time,
                    image = recipe.imageUrls,
                    chefName = chef?.name ?: "",
                    createdAt = recipe.createdAt,
                    chefProfileImageUrl = chef?.imageUrls
                )
            }
    }

}