package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.data.Repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchRecipesViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SearchRecipesState())
    val state = _state.asStateFlow()


    init {
        loadAllRecipes()
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

    // bottom sheet 올리기
    fun showBottomSheet(show: Boolean) {
        _state.update {
            it.copy(showBottomSheet = show)
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