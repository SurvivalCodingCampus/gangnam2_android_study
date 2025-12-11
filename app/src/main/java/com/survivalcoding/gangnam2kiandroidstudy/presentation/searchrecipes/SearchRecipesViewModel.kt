@file:OptIn(FlowPreview::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchRecipesViewModel(
    private val recipeRepository: RecipeRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchRecipesState())
    val state = _state.asStateFlow()

    // 섹시코드
    private val _searchKeywordFlow = state
        .map { it.searchKeyword }
        .debounce(1000)


    init {
        // 있어보임
        viewModelScope.launch {
            _searchKeywordFlow.collect {
                filterRecipes(it)
            }
        }

        viewModelScope.launch {
            val recipes = recipeRepository.getRecipes()
            _state.update {
                it.copy(
                    recipes = recipes,
                    filteredRecipes = recipes,
                )
            }
        }
    }

    private fun filterRecipes(query: String) {
        _state.update {
            it.copy(
                filteredRecipes = it.recipes.filter { recipe ->
                    recipe.title.lowercase().contains(query.lowercase())
                }
            )
        }
    }

    fun searchRecipes(query: String) {
        _state.update {
            it.copy(
                searchKeyword = query,
            )
        }
    }

    fun tapFilterButton() {
        _state.update {
            it.copy(
                showBottomSheet = !it.showBottomSheet,
            )
        }
    }

    fun updateFilterSearchState(filterSearchState: FilterSearchState) {
        _state.update {
            it.copy(
                filterSearchState = filterSearchState,
                showBottomSheet = false,
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository =
                    (this[APPLICATION_KEY] as AppApplication).recipeRepository
                SearchRecipesViewModel(repository)
            }
        }
    }
}