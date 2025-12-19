package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.selector.HomeCategory


sealed interface HomeAction {
    data class SelectCategory(val category: HomeCategory) : HomeAction
    data class ToggleBookmark(val recipeId: Int) : HomeAction
}
