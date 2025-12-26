package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.CopyLinkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val copyLinkUseCase: CopyLinkUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RecipeDetailState())
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

    fun onCopyClick(text: String) {
        copyLinkUseCase.execute(text = text)
    }

    fun onAction(action: RecipeDetailAction) {
        when (action) {
            is RecipeDetailAction.OnValueChange -> {
                toggleTab(action.value)
            }

            else -> {

            }
        }

    }

    fun toggleTab(selectedIndex: Int) {
        _state.update {
            it.copy(selectedIndex = if (selectedIndex == 0) 0 else 1)
        }
    }
}