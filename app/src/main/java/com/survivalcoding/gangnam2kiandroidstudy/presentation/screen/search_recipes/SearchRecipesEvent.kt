package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

sealed interface SearchRecipesEvent {
    data class ShowSnackBar(val str: String) : SearchRecipesEvent
}