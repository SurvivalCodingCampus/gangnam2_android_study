package com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IngridentViewModel(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(IngridentState())
    val state = _state.asStateFlow()


    fun loadRecipeDetail(recipeId: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            val details = getRecipeDetailsUseCase.execute(recipeId)

            _state.update {
                it.copy(
                    isLoading = false,
                    recipe = details.recipe,
                    ingridents = details.ingredients,
                    procedures = details.procedures
                )
            }
        }
    }

    fun toggleTab(selectedIndex: Int) {
        _state.update {
            it.copy(selectedIndex = if (selectedIndex == 0) 0 else 1)
        }
    }
}