package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.LoadHome -> {
                loadRecipes()
            }

            is HomeAction.ClickCategory -> {
                onSelectCategory(action.category)
            }

            is HomeAction.ToggleRecipeBookmark -> {
                val currentBookmarks = _state.value.bookmarkedRecipeIds
                val updatedBookmarks = if (currentBookmarks.contains(action.recipeId)) {
                    currentBookmarks - action.recipeId
                } else {
                    currentBookmarks + action.recipeId
                }
                _state.value = _state.value.copy(bookmarkedRecipeIds = updatedBookmarks)
            }

            else -> Unit
        }
    }


    private fun loadRecipes() {
        viewModelScope.launch {
            runCatching { repository.getRecipes() }
                .onSuccess { recipes ->
                    _state.value = _state.value.copy(
                        allRecipes = recipes,
                        filteredRecipes = recipes,
                        newRecipes = recipes
                            .sortedByDescending { it.createdAt }
                            .take(5),
                        errorMessage = null
                    )
                }
                .onFailure {
                    _state.value = _state.value.copy(
                        errorMessage = "Failed to load recipes"
                    )
                }
        }
    }

    fun onSelectCategory(category: String) {
        val filtered =
            if (category == "All") {
                _state.value.allRecipes
            } else {
                _state.value.allRecipes.filter { it.category == category }
            }

        _state.value = _state.value.copy(
            selectedCategory = category,
            filteredRecipes = filtered
        )
    }
}
