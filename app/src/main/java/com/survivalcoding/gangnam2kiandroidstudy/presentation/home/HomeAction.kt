package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

sealed interface HomeAction {
    data class SelectCategory(val category: String) : HomeAction
    data object SearchRecipe : HomeAction

}