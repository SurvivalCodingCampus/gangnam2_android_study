package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
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
}