package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository,
    private val procedureRepository: ProcedureRepository, // GetRecipeDetailsUseCase 는 추후에 리팩토링으로 작성
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeDetailUiState())
    val uiState = _uiState.asStateFlow()

    val recipeId = savedStateHandle.get<Int>("recipeId") ?: -1

    private fun loadRecipe(id: Int) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            recipeRepository.getRecipeById(id)
                .onSuccess { recipe ->
                    _uiState.update { it.copy(isLoading = false, recipe = recipe ?: return@launch) }
                }
                .onFailure {
                    _uiState.update { it.copy(isLoading = false, message = it.message) }
                }
        }
    }

    private fun loadIngredient(recipeId: Int) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            ingredientRepository.getIngredientByRecipeId(recipeId)
                .onSuccess { ingredients ->
                    _uiState.update { it.copy(isLoading = false, ingredients = ingredients) }
                }
                .onFailure {
                    _uiState.update { it.copy(isLoading = false, message = it.message) }
                }
        }
    }

    private fun loadProcedure(recipeId: Int) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            procedureRepository.getProcedureByRecipeId(recipeId)
                .onSuccess { procedures ->
                    _uiState.update { it.copy(isLoading = false, procedures = procedures) }
                }
                .onFailure {
                    _uiState.update { it.copy(isLoading = false, message = it.message) }
                }
        }
    }

    fun onTabClick(value: Int) {
        _uiState.update { it.copy(selectedTabPosition = value) }
    }

    init {
        loadRecipe(recipeId)
        loadIngredient(recipeId)
        loadProcedure(recipeId)
    }
}