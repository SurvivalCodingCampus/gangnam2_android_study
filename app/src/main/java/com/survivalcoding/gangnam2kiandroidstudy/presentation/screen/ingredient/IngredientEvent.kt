package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

sealed interface IngredientEvent {
    data object NavigateBack : IngredientEvent
    data object ShowShareDialog : IngredientEvent
}