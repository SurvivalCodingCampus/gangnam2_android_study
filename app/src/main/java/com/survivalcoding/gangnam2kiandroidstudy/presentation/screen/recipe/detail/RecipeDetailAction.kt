package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

sealed interface RecipeDetailAction {
    data object OnArrowBackClick : RecipeDetailAction
    data class OnFollowClick(val userId: Int) : RecipeDetailAction
    data class UpdateTabInfo(val index: Int) : RecipeDetailAction

}
