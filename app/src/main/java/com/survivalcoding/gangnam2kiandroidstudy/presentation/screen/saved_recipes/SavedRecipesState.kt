package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class SavedRecipesState(
    val savedRecipesList: List<Recipe> = emptyList()

)