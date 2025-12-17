package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.RecipeAppApplication
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRecipeDetailsViewModel(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow(SavedRecipeDetailsState())
    val state = _state.asStateFlow()


    fun init(id: Int) {
        viewModelScope.launch {
            val result = getRecipeDetailsUseCase.execute(id)
            _state.value =
                _state.value.copy(ingredientList = result.first, procedureList = result.second)
        }
    }

    fun changeValue(isSelectIngredient: Boolean) {
        _state.value = _state.value.copy(isSelectIngredient = isSelectIngredient)
    }


    companion object {
        fun factory(application: RecipeAppApplication): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    SavedRecipeDetailsViewModel(application.savedRecipeDetailsUseCase)
                }
            }
    }
}