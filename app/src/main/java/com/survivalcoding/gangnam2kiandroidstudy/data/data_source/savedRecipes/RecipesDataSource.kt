package com.survivalcoding.gangnam2kiandroidstudy.data.data_source.savedRecipes

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipes

interface RecipesDataSource {
    fun getAllRecipes(): Recipes
    fun deleteSavedRecipe(id: Int)
}
