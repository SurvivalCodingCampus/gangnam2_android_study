package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

data class SearchRecipeState(
    val data: List<Recipe> = emptyList(),
    val loading: Boolean = true,
    val error: String? = null,
    val time: String = "All",
    val rate: Double = 1.0,
    val category: String = "All",
    val showBottomSheet: Boolean = false
)
