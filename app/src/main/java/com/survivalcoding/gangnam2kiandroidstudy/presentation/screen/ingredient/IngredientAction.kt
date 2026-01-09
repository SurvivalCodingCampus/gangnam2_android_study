package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

sealed interface IngredientAction {
    data object OnBackClick : IngredientAction
    data object OnBookmarkClick : IngredientAction
    data class OnTabClick(val index: Int) : IngredientAction
    data object OnShareClick : IngredientAction
    data object OnMoreClick : IngredientAction
    data object OnDismissMoreMenu : IngredientAction
    data object OnDismissShareDialog : IngredientAction
}
