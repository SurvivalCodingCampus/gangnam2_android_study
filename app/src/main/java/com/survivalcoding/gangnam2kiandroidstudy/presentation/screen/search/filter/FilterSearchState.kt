package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search.filter

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory

data class FilterSearchState(
    val time: String = "All",
    val rate: Double = 1.0,
    val category: RecipeCategory = RecipeCategory.ALL
)