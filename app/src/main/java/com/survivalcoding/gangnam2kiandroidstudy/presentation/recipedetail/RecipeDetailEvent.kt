package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

sealed interface RecipeDetailEvent {
    object NavigateUp : RecipeDetailEvent
    data class ShowMessage(val message: String) : RecipeDetailEvent
    data class FollowCompleted(val chefId: Int) : RecipeDetailEvent
    data class ShowShare(val recipe: Recipe) : RecipeDetailEvent
}