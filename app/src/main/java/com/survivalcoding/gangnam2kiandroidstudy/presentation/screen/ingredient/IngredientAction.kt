package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

sealed interface IngredientAction {
    data class SelectTab(val index: Int) : IngredientAction
    data object BackClicked : IngredientAction
    data object FollowClicked : IngredientAction

    data object ShareClicked : IngredientAction

    data object RateClicked : IngredientAction

    data object ReviewClicked : IngredientAction
    
    data object UnsaveClicked : IngredientAction
}