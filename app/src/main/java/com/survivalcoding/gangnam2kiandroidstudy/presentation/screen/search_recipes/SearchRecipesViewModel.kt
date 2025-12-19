package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.FilterSearchState
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter.CategoryFilter
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter.RateFilter
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter.TimeFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class)
class SearchRecipesViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SearchRecipesState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SearchRecipesEvent>()
    val event = _event.asSharedFlow()

    private val searchKeywordFlow = MutableStateFlow("")

    init {
        loadAllRecipes()

        // debounce 검색 실행
        viewModelScope.launch {
            searchKeywordFlow
                .debounce(300)
                .collect { keyword ->
                    applySearch(keyword)
                }
        }
    }

    // action
    fun onAction(action: SearchRecipesAction) {
        when (action) {

            is SearchRecipesAction.KeywordChanged -> {
                updateSearchKeyword(action.keyword)
            }

            SearchRecipesAction.FilterClicked -> {
                showBottomSheet(true)
            }

            SearchRecipesAction.FilterDismissed -> {
                showBottomSheet(false)
            }

            is SearchRecipesAction.FilterApplied -> {
                applyFilters(action.filter)
                showBottomSheet(false)
            }

            is SearchRecipesAction.RecipeClicked -> {
                onRecipeClick(action.recipeId)
            }
        }
    }

    // navigation event
    private fun onRecipeClick(recipeId: Int) {
        viewModelScope.launch {
            _event.emit(
                SearchRecipesEvent.NavigateToRecipeDetail(recipeId)
            )
        }
    }


    // 데이터 로딩
    private fun loadAllRecipes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val recipeList = recipeRepository.getRecipes()

            _state.update {
                it.copy(
                    recipes = recipeList,
                    filteredRecipes = recipeList,
                    isLoading = false
                )
            }

        }
    }

    // 검색어
    private fun updateSearchKeyword(keyword: String) {
        _state.update { it.copy(searchKeyword = keyword) } // // UI 즉시 업데이트

        searchKeywordFlow.value = keyword // debounce Flow 업데이트
    }

    // 검색어 결과
    private fun applySearch(keyword: String) {
        _state.update { state ->
            // 검색어 적용
            var result = state.recipes.filter {
                it.title.contains(keyword, ignoreCase = true)
            }

            // 필터 적용
            result = result.filter { recipe ->
                filterMatches(recipe, state.filterState)
            }

            // 정렬 적용
            result = when (state.filterState.time) {
                TimeFilter.ALL -> result
                TimeFilter.NEWEST -> result.sortedByDescending { it.createdAt }
                TimeFilter.OLDEST -> result.sortedBy { it.createdAt }
                TimeFilter.POPULARITY -> result.sortedByDescending { it.rating }
            }

            state.copy(filteredRecipes = result)
        }
    }

    // 필터
    private fun applyFilters(filter: FilterSearchState) {
        val current = _state.value

        val wasFilterActive = !current.filterState.isDefault()
        val isNowDefault = filter.isDefault()

        var result: List<Recipe> = current.recipes

        // 검색어
        result = result.filter {
            it.title.contains(current.searchKeyword, ignoreCase = true)
        }

        // 필터
        result = result.filter { recipe ->
            filterMatches(recipe, filter)
        }

        // 정렬
        result = when (filter.time) {
            TimeFilter.ALL -> result
            TimeFilter.NEWEST -> result.sortedByDescending { it.createdAt }
            TimeFilter.OLDEST -> result.sortedBy { it.createdAt }
            TimeFilter.POPULARITY -> result.sortedByDescending { it.rating }
        }

        //  상태 업데이트
        _state.update {
            it.copy(
                filteredRecipes = result,
                filterState = filter
            )
        }

        // 이벤트
        viewModelScope.launch {
            when {
                wasFilterActive && isNowDefault -> {
                    _event.emit(
                        SearchRecipesEvent.ShowSnackBar("필터가 취소되었습니다.")
                    )
                }

                !wasFilterActive && !isNowDefault -> {
                    _event.emit(
                        SearchRecipesEvent.ShowSnackBar("필터가 적용되었습니다.")
                    )
                }
            }
        }
    }

    // 개별 필터 조건 매칭
    private fun filterMatches(recipe: Recipe, filter: FilterSearchState): Boolean {
        // Category
        if (filter.category != CategoryFilter.ALL &&
            recipe.category != filter.category.label
        ) return false

        // Rate : 선택한 평점 이하만 표시
        if (filter.rate != RateFilter.FIVE &&
            recipe.rating > filter.rate.value
        ) return false

        return true
    }

    // bottom sheet 올리기
    private fun showBottomSheet(show: Boolean) {
        _state.update { it.copy(showBottomSheet = show) }
    }

}