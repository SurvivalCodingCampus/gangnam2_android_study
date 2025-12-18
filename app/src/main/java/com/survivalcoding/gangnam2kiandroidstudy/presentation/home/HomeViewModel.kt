package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetHomeRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeRecipesUseCase: GetHomeRecipesUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.CategorySelected -> onSelectCategory(action.category)
            is HomeAction.RecipeBookmarked -> onRecipeBookmarked(action.recipe)
        }
    }

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val homeRecipes = getHomeRecipesUseCase.execute()
            _state.update {
                it.copy(
                    homeRecipes = homeRecipes,
                    filteredHomeRecipes = homeRecipes,
                    isLoading = false
                )
            }
        }
    }

    private fun onRecipeBookmarked(recipe: Recipe) {
        viewModelScope.launch {
            toggleBookmarkUseCase.execute(recipe)
            _state.update { currentState ->
                val newHomeRecipes = currentState.homeRecipes.map {
                    if (it.recipe.id == recipe.id) it.copy(isBookmarked = !it.isBookmarked) else it
                }
                currentState.copy(homeRecipes = newHomeRecipes)
            }
            onSelectCategory(_state.value.selectedCategory)
        }
    }

    private fun onSelectCategory(category: String) {
        _state.update { currentState ->
            val categoryFilteredList = if (category.equals("All", ignoreCase = true)) {
                currentState.homeRecipes
            } else {
                currentState.homeRecipes.filter { it.recipe.category.equals(category, ignoreCase = true) }
            }

            currentState.copy(
                filteredHomeRecipes = categoryFilteredList,
                selectedCategory = category
            )
        }
    }
}
