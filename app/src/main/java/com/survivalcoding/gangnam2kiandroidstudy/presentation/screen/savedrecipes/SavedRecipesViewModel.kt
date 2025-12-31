@file:OptIn(ExperimentalCoroutinesApi::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    getSavedRecipesUseCase: GetSavedRecipesUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SavedRecipesUiState())
    val uiState: StateFlow<SavedRecipesUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SavedRecipesEvent>()
    val event = _event.asSharedFlow()

    init {
        getSavedRecipesUseCase().mapLatest { result ->
            when (result) {
                is AppResult.Success -> {
                    _uiState.update { it.copy(recipes = result.data) }
                }
                is AppResult.Error -> {
                    _uiState.update { it.copy(recipes = emptyList()) }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onAction(action: SavedRecipesAction) {
        when (action) {
            is SavedRecipesAction.OnBookmarkClick -> toggleBookmark(action.recipeId)
            is SavedRecipesAction.OnCardClick -> navigateToDetails(action.recipeId)
        }
    }

    private fun toggleBookmark(recipeId: Long) {
        viewModelScope.launch {
            toggleBookmarkUseCase(recipeId)
        }
    }

    private fun navigateToDetails(recipeId: Long) {
        viewModelScope.launch {
            _event.emit(SavedRecipesEvent.NavigateToDetails(recipeId))
        }
    }
}