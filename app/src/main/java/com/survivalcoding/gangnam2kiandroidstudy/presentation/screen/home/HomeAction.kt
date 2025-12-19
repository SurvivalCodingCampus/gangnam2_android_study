package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

sealed interface HomeAction {
    data class OnSelectedCategory(val recipeName: String) : HomeAction
    data object OnSearchClicked : HomeAction
    data class OnRecipeItemClicked(val recipe: Recipe) : HomeAction

}