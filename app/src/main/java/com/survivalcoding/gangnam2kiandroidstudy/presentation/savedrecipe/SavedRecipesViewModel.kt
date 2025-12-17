package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedRecipesViewModel @Inject constructor(
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