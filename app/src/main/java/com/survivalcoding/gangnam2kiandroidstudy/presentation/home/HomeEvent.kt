package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

sealed interface HomeEvent {
    data object NavigateToSearch : HomeEvent
    data class NavigateToRecipeDetail(val recipeId: Int) : HomeEvent
    data class ShowError(val message: String) : HomeEvent
}