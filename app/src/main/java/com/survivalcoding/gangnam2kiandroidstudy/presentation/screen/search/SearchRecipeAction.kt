package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.filter.FilterSearchState

sealed interface SearchRecipeAction {

    data object OnSearchDone : SearchRecipeAction
    data object OnFilterSettingClick : SearchRecipeAction

    data class UpdateQuery(val query: String) : SearchRecipeAction
    data class UpdateFilterSearch(val filterSearch: FilterSearchState) : SearchRecipeAction
}