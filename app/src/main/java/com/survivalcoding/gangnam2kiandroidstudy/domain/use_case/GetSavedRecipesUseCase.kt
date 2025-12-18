package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import javax.inject.Inject

class GetSavedRecipesUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    private val recipeRepository: RecipeRepository
) {
    suspend fun execute(): Result<List<Recipe>, String> {
        return try {
            val bookmarkedIds = bookmarkRepository.getSavedRecipeIds()
            val recipes = recipeRepository.getRecipesByIds(bookmarkedIds)
            Result.Success(recipes)
        } catch (e: Exception) {
            Result.Error("북마크된 레시피를 찾을 수 없습니다. ")
        }
    }
}
