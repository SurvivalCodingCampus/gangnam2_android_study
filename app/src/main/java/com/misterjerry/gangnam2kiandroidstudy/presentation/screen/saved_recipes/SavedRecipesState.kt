package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import com.misterjerry.gangnam2kiandroidstudy.domain.model.Recipe

data class SavedRecipesState(
    val savedRecipesList: List<Recipe> = emptyList()

)