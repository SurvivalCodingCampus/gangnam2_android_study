package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

sealed interface RecipeDetailAction {
    data class TabClick(val position: Int) : RecipeDetailAction
    data class FollowClick(val chefId: Int) : RecipeDetailAction
    object BackClick : RecipeDetailAction
    data class MenuClick(val isShow: Boolean) : RecipeDetailAction
    object ShareClick : RecipeDetailAction
    object CopyLinkClick : RecipeDetailAction
    object DismissShareDialog : RecipeDetailAction
}