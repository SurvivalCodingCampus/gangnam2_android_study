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
            .debounce(100)
            .onEach { query ->
                filterRecipes(query)
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

    private fun filterRecipes(query: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val newSearchText = if (query.isBlank()) {
                "Recent Search"
            } else {
                "Search Result"
            }
            val filteredRecipeText = if (query.isBlank()) {
                ""
            } else {
                "${_state.value.recipes.filter { recipe ->
                    recipe.title.contains(query, ignoreCase = true)
                }.size} results"
            }
            _state.update {
                it.copy(
                    filteredRecipes = it.recipes.filter { recipe ->
                        recipe.title.contains(query, ignoreCase = true)
                    },
                    filteredRecipesText = filteredRecipeText,
                    searchText = newSearchText,
                    isLoading = false
                )
            }
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