package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

data class FilterSearchState(
    val time: String = "All",
    val rate: Int = 5,
    val category: String = "All"
)