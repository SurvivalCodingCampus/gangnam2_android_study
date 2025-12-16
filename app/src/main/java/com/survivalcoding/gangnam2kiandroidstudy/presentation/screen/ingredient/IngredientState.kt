package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class IngredientState(
    val recipe: Recipe? = null,
    val author: Chef? = null,
    val ingredients: List<IngredientItem> = emptyList(),
    val procedures: List<String> = emptyList(),
    val selectedTab: Int = 0,
    val isFollowing: Boolean = false
)
