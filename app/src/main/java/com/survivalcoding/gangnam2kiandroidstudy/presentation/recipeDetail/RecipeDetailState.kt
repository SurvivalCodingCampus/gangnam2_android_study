package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipeDetail

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingrident
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val selectedIndex: Int = 0,
    val ingridents: List<Ingrident> = emptyList(),
    val procedures: List<Procedure> = emptyList(),
)
