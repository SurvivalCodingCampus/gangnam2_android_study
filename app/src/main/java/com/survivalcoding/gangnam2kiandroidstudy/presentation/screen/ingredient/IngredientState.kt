package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeDetail

data class IngredientState(
    val recipeDetail: RecipeDetail? = null,
    val selectedTab: Int = 0,
    val isFollowing: Boolean = false
)

