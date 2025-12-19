package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

sealed interface SearchRecipesEvent {
    data class ShowSnackBar(val message: String) : SearchRecipesEvent
    data class OnSelectRecipes(val id: Int) : SearchRecipesEvent
}