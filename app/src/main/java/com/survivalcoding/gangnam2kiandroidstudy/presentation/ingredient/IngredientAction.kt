package com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient

sealed interface IngredientAction {
    data class TabSelected(val tab: String) : IngredientAction
    data object ToggleMoreOptions : IngredientAction
    data object ToggleShareDialog : IngredientAction
    data object CopyLink : IngredientAction
}
