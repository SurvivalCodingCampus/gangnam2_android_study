package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.DeleteSavedRecipeUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetAllRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
    private val deleteSavedRecipeUseCase: DeleteSavedRecipeUseCase,
    private val getAllRecipesUseCase: GetAllRecipesUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(SavedRecipesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val allRecipes = getAllRecipesUseCase.execute()
            val savedRecipeIds = getSavedRecipesUseCase.execute().map { it.recipeId }
            _state.value =
                _state.value.copy(
                    savedRecipesList = allRecipes
                        .filter { savedRecipeIds.contains(it.id) })
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            deleteSavedRecipeUseCase.execute(id)
            _state.value = _state.value.copy(
                savedRecipesList = _state.value.savedRecipesList.filter { id != it.id }
            )
        }
    }


}