package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

sealed interface HomeAction {
    data class CategorySelected(val category: String) : HomeAction
    data class RecipeBookmarked(val recipe: Recipe) : HomeAction
}
