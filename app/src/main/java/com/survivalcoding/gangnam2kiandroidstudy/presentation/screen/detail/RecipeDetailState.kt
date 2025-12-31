package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.detail

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeIngredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.recipe.Recipe

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val selectedIndex: Int = 0,
    val ingredients: List<RecipeIngredient> = emptyList(),
    val procedures: List<Procedure> = emptyList(),
    // 드롭다운
    val isDropDownExpanded: Boolean = false,
    // 공유 다이얼로그
    val isShareDialogVisible: Boolean = false,
    val shareLink: String = "",
    val message: String? = null, // Added for user feedback messages
)