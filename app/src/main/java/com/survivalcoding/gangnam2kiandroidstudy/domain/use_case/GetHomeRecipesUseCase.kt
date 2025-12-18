package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.HomeRecipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import javax.inject.Inject

class GetHomeRecipesUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val bookmarkRepository: BookmarkRepository,
    private val chefRepository: ChefRepository
) {
    suspend fun execute(): List<HomeRecipe> {
        val recipes = recipeRepository.getRecipes()
        val chefs = chefRepository.getChefs()
        val bookmarkedRecipeIds = bookmarkRepository.getBookmarks().map { it.id }.toSet()

        val chefsByName = chefs.associateBy { it.name }

        return recipes.map { recipe ->
            val chef = chefsByName[recipe.chef]
            HomeRecipe(
                recipe = recipe,
                chefImageUrl = chef?.image ?: "",
                isBookmarked = bookmarkedRecipeIds.contains(recipe.id)
            )
        }
    }
}
