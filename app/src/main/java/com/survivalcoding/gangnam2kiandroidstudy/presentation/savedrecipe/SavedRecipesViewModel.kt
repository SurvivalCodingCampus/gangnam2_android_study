package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SavedRecipesState())
    val state = _state.asStateFlow()

    init {
        getRecipes(0)
    }

    fun getRecipes(id: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            val recipes = getSavedRecipesUseCase.execute(id)

            _state.update {
                it.copy(
                    isLoading = false,
                    recipes = recipes
                )
            }
        }
    }
}