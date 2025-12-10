package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeCard

data class SearchRecipesState(
    val recipes: List<RecipeCard> = emptyList(), // 전체 레시피 목록
    val filteredRecipes: List<RecipeCard> = emptyList(), // 검색된 목록
    val searchKeyword: String = "",
    val isLoading: Boolean = false, // 로딩 여부
    val showBottomSheet: Boolean = false
)
