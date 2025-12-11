package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchRecipeViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchRecipeState())
    val uiState: StateFlow<SearchRecipeState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        fetchSearchRecipes("")

        viewModelScope.launch {
            _searchQuery.drop(1)
                .debounce(1000)
                .distinctUntilChanged()
                .collect { query ->
                    fetchSearchRecipes(query)
                }
        }
    }

    fun updateSearch(query: String) {
        _searchQuery.value = query
    }

    fun updateFilterTime(time: String) {
        _uiState.update {
            it.copy(time = time)
        }
    }

    fun updateFilterRate(rate: String) {
        _uiState.update {
            it.copy(rate = rate.toDouble())
        }
    }

    fun updateFilterCategory(category: String) {
        _uiState.update {
            it.copy(category = category)
        }
    }

    fun updateFilter(time: String, rate: Double, category: String) {
        _uiState.update {
            it.copy(
                time = time,
                rate = rate,
                category = category
            )
        }

        fetchSearchRecipes(_searchQuery.value)
    }

    fun performSearch() {
        fetchSearchRecipes(_searchQuery.value)
    }

    fun showBottomSheet() {
        _uiState.update {
            it.copy(showBottomSheet = true)
        }
    }

    fun hideBottomSheet() {
        _uiState.update {
            it.copy(showBottomSheet = false)
        }
    }

    private fun fetchSearchRecipes(query: String) {
        try {
            viewModelScope.launch {
                val response = recipeRepository.search(
                    query = query,
                    _uiState.value.time,
                    _uiState.value.rate,
                    _uiState.value.category
                )

                when (response) {
                    is Result.Success -> _uiState.update {
                        it.copy(data = response.data, loading = false)
                    }

                    is Result.Failure -> {
                        _uiState.update {
                            it.copy(loading = false, error = response.error.toString())
                        }
                    }
                }
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(loading = false, error = "Error fetching ${e.message}")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val recipeRepository = (this[APPLICATION_KEY] as AppApplication).recipeRepository
                SearchRecipeViewModel(recipeRepository)
            }
        }
    }
}