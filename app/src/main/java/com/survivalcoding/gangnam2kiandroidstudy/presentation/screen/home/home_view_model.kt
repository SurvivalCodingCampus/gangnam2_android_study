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

            else -> Unit
        }
    }


    private fun loadRecipes() {
        viewModelScope.launch {
            val recipes = repository.getRecipes()
            _state.value = _state.value.copy(
                allRecipes = recipes,
                filteredRecipes = recipes,

                newRecipes = recipes
                    .sortedByDescending { it.createdAt }
                    .take(5)
            )
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
