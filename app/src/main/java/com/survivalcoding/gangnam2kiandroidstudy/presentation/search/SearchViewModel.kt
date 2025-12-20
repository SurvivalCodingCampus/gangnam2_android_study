package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetAllRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SearchRecipeByKeywordUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val searchRecipeByKeywordUseCase: SearchRecipeByKeywordUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private fun fetchAllRecipes() {
        _uiState.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            getAllRecipesUseCase()
                .onSuccess { recipes ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            allRecipes = recipes
                        )
                    }
                }
                .onFailure {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            message = it.message
                        )
                    }
                }
        }
    }

    private suspend fun fetchFilteredRecipesByKeyword(keyword: String) {
        searchRecipeByKeywordUseCase(keyword)
            .onSuccess { recipes ->
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        filteredRecipes = recipes
                    )
                }
            }
            .onFailure {
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        message = it.message
                    )
                }
            }
    }

    @OptIn(FlowPreview::class)
    private fun subscribeSearchKeywordFlow() {
        viewModelScope.launch {
            uiState.map { it.searchKeyword }
                .debounce(500)
                .collectLatest { keyword ->
                    fetchFilteredRecipesByKeyword(keyword)
                }
        }
    }

    fun onSearchKeywordChange(value: String) {
        _uiState.update { it.copy(searchKeyword = value) }
    }

    fun onFilterButtonClick(value: Boolean) {
        _uiState.update { it.copy(isShowBottomSheet = value) }
    }

    fun onFilterComplete(value: FilterSearchState) {
        _uiState.update { it.copy(filterSearchState = value) }
    }

    init {
        fetchAllRecipes()
        subscribeSearchKeywordFlow()
    }
}