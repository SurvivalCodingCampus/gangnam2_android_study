package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    recipeDetailsUiState: RecipeDetailsUiState = RecipeDetailsUiState(),
) : ViewModel() {
    private val _uiState: MutableStateFlow<RecipeDetailsUiState> =
        MutableStateFlow(recipeDetailsUiState)
    val uiState: StateFlow<RecipeDetailsUiState> = _uiState.asStateFlow()

    fun fetchRecipeDetails(recipeId: Long) {
        setLoading(true)

        viewModelScope.launch {
            try {
                when (val result = getRecipeDetailsUseCase(recipeId)) {
                    is AppResult.Success -> {
                        _uiState.update {
                            it.copy(
                                recipe = result.data.recipe,
                                profile = result.data.profile,
                                ingredients = result.data.ingredients,
                                procedures = result.data.procedures,
                            )
                        }
                    }
                    is AppResult.Error -> {
                        _uiState.update {
                            it.copy(
                                recipe = null,
                                profile = null,
                                ingredients = emptyList(),
                                procedures = emptyList(),
                            )
                        }
                    }
                }
            } finally {
                setLoading(false)
            }
        }
    }

    fun changeTab(selectedIndex: Int) {
        _uiState.update { it.copy(selectedTabIndex = selectedIndex) }
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    companion object {
        fun factory(application: AppApplication): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RecipeDetailsViewModel(
                    application.getRecipeDetailsUseCase,
                )
            }
        }
    }
}