package com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.CopyLinkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val copyLinkUseCase: CopyLinkUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(IngredientState())
    val state = _state.asStateFlow()

    fun onAction(action: IngredientAction) {
        when (action) {
            is IngredientAction.TabSelected -> {
                _state.update { it.copy(selectedTab = action.tab) }
            }
            IngredientAction.ToggleMoreOptions -> {
                _state.update { it.copy(isMoreOptionsOpen = !it.isMoreOptionsOpen) }
            }
            IngredientAction.ToggleShareDialog -> {
                _state.update { 
                    it.copy(
                        isShareDialogVisible = !it.isShareDialogVisible,
                        isMoreOptionsOpen = false // Close menu when opening dialog
                    ) 
                }
            }
            IngredientAction.CopyLink -> {
                copyLinkUseCase.execute(_state.value.recipeLink)
            }
        }
    }

    fun loadRecipe(recipeId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val details = getRecipeDetailsUseCase.execute(recipeId)
            _state.update {
                it.copy(
                    recipe = details.recipe,
                    procedures = details.procedures,
                    isLoading = false,
                    recipeLink = "https://www.gangnam2ki.com/recipe/${details.recipe.id}"
                )
            }
        }
    }
}
