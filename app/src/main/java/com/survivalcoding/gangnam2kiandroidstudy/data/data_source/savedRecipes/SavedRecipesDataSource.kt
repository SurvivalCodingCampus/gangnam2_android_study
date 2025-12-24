package com.survivalcoding.gangnam2kiandroidstudy.data.data_source.savedRecipes

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipes

interface SavedRecipesDataSource {
    fun getSavedRecipes(): Recipes
    fun deleteSavedRecipe(id: Int)
}
