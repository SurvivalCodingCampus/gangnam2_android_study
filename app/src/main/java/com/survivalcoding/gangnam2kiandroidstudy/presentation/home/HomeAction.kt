package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

sealed interface HomeAction {
    data class SelectCategory(val category: String) : HomeAction
    data object SearchRecipe : HomeAction
    data class SelectRecipe(val id: Int) : HomeAction
    data class BookmarkRecipe(val id: Int) : HomeAction
}