package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchRecipesViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SearchRecipeState())
    val state = _state.asStateFlow()

    init {
        getAllRecipes()

        _state.map { it.searchQuery }
            .debounce(300)
            .onEach { query ->
                filterRecipes()
            }
            .launchIn(viewModelScope)
    }

    fun getAllRecipes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update {
                it.copy(
                    recipes = recipeRepository.getRecipes(),
                    filteredRecipes = recipeRepository.getRecipes(),
                    isLoading = false
                )
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _state.update { currentState ->
            currentState.copy(searchQuery = query)
        }
    }

    fun applyFilters(newFilterState: FilterSearchState) {
        _state.update {
            it.copy(filterState = newFilterState)
        }
        filterRecipes()
    }
    fun showBottomSheet(isFilterOpen: Boolean) {
        _state.update {
            it.copy(showBottomSheet = isFilterOpen)
        }
    }

    private fun filterRecipes() {
        val query = _state.value.searchQuery
        val filters = _state.value.filterState
        var resultList = _state.value.recipes

        if (query.isNotBlank()) {
            resultList = resultList.filter { recipe ->
                recipe.title.contains(query, ignoreCase = true)
            }
        }

        if (filters.selectedCategoryText != null && filters.selectedCategoryText != "All") {
            resultList = resultList.filter { recipe ->
                recipe.category.equals(filters.selectedCategoryText, ignoreCase = true)
            }
        }

        filters.selectedRateText?.let { rateString ->
            val rate = rateString.toDoubleOrNull() ?: 0.0
            resultList = resultList.filter { recipe ->
                recipe.rating >= rate
            }
        }

        if (filters.selectedTimeText != null && filters.selectedTimeText != "All") {
            val currentTime = System.currentTimeMillis()
            val day = 24 * 60 * 60 * 1000L

            resultList = when (filters.selectedTimeText) {
                "Newest" -> {
                    val sevenDaysAgo = currentTime - (7 * day)
                    resultList.filter { it.createdAt >= sevenDaysAgo }
                }
                "Oldest" -> {
                    val thirtyDaysAgo = currentTime - (30 * day)
                    resultList.filter { it.createdAt <= thirtyDaysAgo }
                }
                "Popularity" -> {
                    resultList.filter { it.rating >= 4.5 }
                }
                else -> resultList
            }
        }

        val isCategoryActive = filters.selectedCategoryText != null && filters.selectedCategoryText != "All"
        val isRateActive = filters.selectedRateText != null
        val isTimeActive = filters.selectedTimeText != null && filters.selectedTimeText != "All"

        val isFilterActive = isCategoryActive || isRateActive || isTimeActive

        val newSearchText = if (query.isBlank()) "Recent Search" else "Search Result"

        val filteredRecipeText = if (!(query.isNotBlank()) && !isFilterActive) {
            ""
        } else {
            "${resultList.size} results"
        }

        _state.update {
            it.copy(
                filteredRecipes = resultList,
                filteredRecipesText = filteredRecipeText,
                searchText = newSearchText,
                isLoading = false
            )
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = (this[APPLICATION_KEY] as AppApplication).recipeRepository
                SearchRecipesViewModel(repository)
            }
        }
    }


}