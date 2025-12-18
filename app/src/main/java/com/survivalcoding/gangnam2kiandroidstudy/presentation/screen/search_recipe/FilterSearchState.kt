package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

data class FilterSearchState(
    val time: String = "",
    val rating: Int? = null,
    val categories: Set<String> = emptySet(),
)