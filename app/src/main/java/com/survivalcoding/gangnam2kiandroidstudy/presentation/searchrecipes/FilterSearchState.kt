package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

data class FilterSearchState(
    val time: String = "",
    val rating: Int? = null,
    val categories: Set<String> = emptySet(),
)
