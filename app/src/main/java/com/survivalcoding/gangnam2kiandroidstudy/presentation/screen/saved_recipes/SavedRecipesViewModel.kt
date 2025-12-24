package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.DeleteSavedRecipeUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
    private val deleteSavedRecipeUseCase: DeleteSavedRecipeUseCase
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
        viewModelScope.launch {
            deleteSavedRecipeUseCase.execute(id)
            val result = _state.value.savedRecipesList.filter { id != it.id }
            _state.value = _state.value.copy(
                savedRecipesList = result
            )
        }
    }


}