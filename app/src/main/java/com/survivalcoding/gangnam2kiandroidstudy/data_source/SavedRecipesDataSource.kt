package com.survivalcoding.gangnam2kiandroidstudy.data_source

import com.survivalcoding.gangnam2kiandroidstudy.model.RecipeDto

interface SavedRecipesDataSource {
    fun getSavedRecipes(): RecipeDto
}
