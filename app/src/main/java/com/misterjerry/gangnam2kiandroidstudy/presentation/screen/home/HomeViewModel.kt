package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.misterjerry.gangnam2kiandroidstudy.domain.model.Recipe
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.RecipesRepository
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: RecipesRepository,
    private val savedRecipesRepository: SavedRecipesRepository
) : ViewModel() {
    private var cachedRecipes: List<Recipe> = emptyList()
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val savedRecipeIds = savedRecipesRepository.getSavedRecipes().map { it.recipeId }
            cachedRecipes = repository.getAllRecipes().map {
                it.copy(isSaved = savedRecipeIds.contains(it.id))
            }
            _state.value = _state.value.copy(
                selectedCategory = "All", resultRecipes = cachedRecipes.toImmutableList()
            )
        }
        Log.d("HomeViewModel", "init: ${_state.value}")
    }

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnSelectedCategory -> onSelectedCategory(action.recipeName)
            else -> {}
        }

    }

    private fun onSelectedCategory(category: String) {
        if (category == "All") {
            _state.value = _state.value.copy(
                selectedCategory = category, resultRecipes = cachedRecipes.toImmutableList()
            )
        } else {
            _state.value = _state.value.copy(
                selectedCategory = category,
                resultRecipes = cachedRecipes.filter { it.category == category }.toImmutableList()
            )
        }
        Log.d("HomeViewModel", "onSelectedCategory: ${_state.value}")
    }

    fun addSavedRecipe(recipeId: Int) {
        viewModelScope.launch {
            savedRecipesRepository.addSavedRecipe(recipeId)
        }

        cachedRecipes = cachedRecipes.map {
            if (it.id == recipeId) it.copy(isSaved = true) else it
        }
        _state.value = _state.value.copy(
            resultRecipes = _state.value.resultRecipes.map {
                if (it.id == recipeId) {
                    it.copy(isSaved = true)
                } else {
                    it
                }
            }.toImmutableList(),
        )

    }

    fun deleteSavedRecipe(recipeId: Int) {
        viewModelScope.launch {

            savedRecipesRepository.deleteSavedRecipe(recipeId)
        }
        cachedRecipes = cachedRecipes.map {
            if (it.id == recipeId) it.copy(isSaved = false) else it
        }
        _state.value = _state.value.copy(
            resultRecipes = _state.value.resultRecipes.map {
                if (it.id == recipeId) {
                    it.copy(isSaved = false)
                } else {
                    it
                }
            }.toImmutableList()
        )
    }


}