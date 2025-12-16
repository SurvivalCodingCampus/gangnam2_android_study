package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(IngredientState())
    val state = _state.asStateFlow()


    fun loadIngredients(recipeId: Int) {
        viewModelScope.launch {
            val detail = getRecipeDetailUseCase.execute(recipeId) ?: return@launch

            _state.update {
                it.copy(
                    recipeDetail = detail
                )
            }
        }
    }

    fun onTabSelected(index: Int) {
        _state.update {
            it.copy(selectedTab = index)
        }
    }

    companion object {
        fun factory(application: AppApplication): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    IngredientViewModel(
                        getRecipeDetailUseCase = application.getRecipeDetailUseCase
                    )
                }
            }
    }

}