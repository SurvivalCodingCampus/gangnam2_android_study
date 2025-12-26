package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipeDetail


sealed interface RecipeDetailAction {
    data class OnValueChange(val value: Int) : RecipeDetailAction
    data object OnDropDownClick: RecipeDetailAction
    data object OnDropDownDismiss: RecipeDetailAction
    data object OnShareClick: RecipeDetailAction

}