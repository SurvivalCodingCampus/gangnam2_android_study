package com.misterjerry.gangnam2kiandroidstudy.data.data_source.savedRecipes

import com.misterjerry.gangnam2kiandroidstudy.domain.model.Recipes

interface RecipesDataSource {
    fun getAllRecipes(): Recipes
    fun deleteSavedRecipe(id: Int)
}
