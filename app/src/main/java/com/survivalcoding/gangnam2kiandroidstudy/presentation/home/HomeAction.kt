package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

sealed interface HomeAction {
    data class OnCategoryClick(val category: String) : HomeAction // 카테고리 버튼 클릭 시 호출될 함수
    data class OnSearchQueryChange(val query: String) : HomeAction // 검색어 변경 시 호출될 함수
    data object OnSearchClick : HomeAction // 검색 버튼 클릭 시 호출될 함수
    data class OnRecipeClick(val recipeId: Long) : HomeAction // 레시피 클릭 시 호출될 함수
}

