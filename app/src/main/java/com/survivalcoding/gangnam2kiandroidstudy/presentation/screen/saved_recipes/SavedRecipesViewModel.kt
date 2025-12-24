package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(SavedRecipesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value =
                _state.value.copy(savedRecipesList = getSavedRecipesUseCase.execute())
        }
    }

    fun delete(id: Int) {
        val result = _state.value.savedRecipesList.filterIndexed { _, recipe -> id != recipe.id }
        _state.value = _state.value.copy(
            savedRecipesList = result
        )
    }


}