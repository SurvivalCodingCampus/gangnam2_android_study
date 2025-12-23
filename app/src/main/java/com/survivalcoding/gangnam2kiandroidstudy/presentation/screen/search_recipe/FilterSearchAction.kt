package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

sealed interface FilterSearchAction {
    data class OnApplyFilterClick(val filter: FilterSearchState) : FilterSearchAction
    data object OnDismissFilter : FilterSearchAction
}