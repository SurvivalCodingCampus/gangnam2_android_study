package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class RecipeDetailsUiState(
    val recipe: Recipe? = null,
    val profile: Profile? = null,
    val ingredients: List<Ingredient> = emptyList(),
    val procedures: List<Procedure> = emptyList(),
    val reviewCount: Int = 0,
    val isLoading: Boolean = false,
    val isFollowing: Boolean = false,
    val selectedTabIndex: Int = 0,
)
