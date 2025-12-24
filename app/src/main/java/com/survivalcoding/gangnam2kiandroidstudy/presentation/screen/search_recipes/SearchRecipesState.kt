package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.FilterSearchState

data class SearchRecipesState(
    val recipes: List<Recipe> = emptyList(), // 전체 레시피 목록
    val filteredRecipes: List<Recipe> = emptyList(), // 검색된 목록
    val searchKeyword: String = "",
    val isLoading: Boolean = false, // 로딩 여부
    val showBottomSheet: Boolean = false,
    val filterState: FilterSearchState = FilterSearchState(),
    val hasRequestedFocus: Boolean = false,
) {
    // 검색어 유무에 따라 UI 문구 결정
    val headerTitleRes: Int
        get() = if (searchKeyword.isBlank() && !isFilterActive)
            R.string.recent_search_subtitle
        else
            R.string.search_result_subtitle

    // 검색 결과 개수
    val resultCountResId: Int?
        get() = if (searchKeyword.isBlank() && !isFilterActive) null else R.string.result_count_format

    // 필터 만 눌렀을 떄 (기본값인지 판단)
    val isFilterActive: Boolean
        get() = filterState != FilterSearchState()
}
