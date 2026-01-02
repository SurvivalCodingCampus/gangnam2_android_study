package com.misterjerry.gangnam2kiandroidstudy.domain.use_case

import com.misterjerry.gangnam2kiandroidstudy.data.saved_recipes.SavedRecipesDao

class DeleteSavedRecipeUseCase(
    private val dao: SavedRecipesDao
) {
    suspend fun execute(id: Int) {
        dao.deleteSavedRecipe(id)
    }


}