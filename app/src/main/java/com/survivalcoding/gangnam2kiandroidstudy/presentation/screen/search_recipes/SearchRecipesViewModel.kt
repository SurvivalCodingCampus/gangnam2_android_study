package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.FilterSearchState
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter.CategoryFilter
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter.RateFilter
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.filter.TimeFilter
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchRecipesViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SearchRecipesState())
    val state = _state.asStateFlow()

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

    // 화면 최초 진입시 모든 레시피
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
    fun updateSearchKeyword(keyword: String) {
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

    // bottom sheet 올리기
    fun showBottomSheet(show: Boolean) {
        _state.update { it.copy(showBottomSheet = show) }
    }

    // 검색어 결과
    fun applyFilters(filter: FilterSearchState) {

        _state.update { current ->

            var result: List<Recipe> = current.recipes

            // 검색어
            result = result.filter { recipe ->
                recipe.title.contains(current.searchKeyword, ignoreCase = true)
            }

            // Category / Rate
            result = result.filter { recipe ->
                filterMatches(recipe, filter)
            }

            // 3) TimeFilter 정렬
            result = when (filter.time) {
                TimeFilter.ALL -> result
                TimeFilter.NEWEST -> result.sortedByDescending { it.createdAt }
                TimeFilter.OLDEST -> result.sortedBy { it.createdAt }
                TimeFilter.POPULARITY -> result.sortedByDescending { it.rating }
            }


            current.copy(
                filteredRecipes = result,
                filterState = filter
            )
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


    companion object {
        fun factory(application: AppApplication): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    SearchRecipesViewModel(
                        recipeRepository = application.recipeRepository
                    )
                }
            }
    }
}