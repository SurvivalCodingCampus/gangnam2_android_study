package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedRecipesViewModel @Inject constructor(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SavedRecipesState())
    val state: StateFlow<SavedRecipesState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<SavedRecipesEvent>()
    val event = _event.asSharedFlow()

    init {
        loadSavedRecipes()
    }

    fun onAction(action: SavedRecipesAction) {

        when (action) {
            is SavedRecipesAction.RecipeClicked -> {
                onRecipeClick(action.recipeId)
            }

            is SavedRecipesAction.BookmarkClicked -> {
                onBookmarkClick(action.recipeId)
            }

            is SavedRecipesAction.ReachedBottom -> {
                emitReachedBottomSnackBar()
            }
        }
    }

    // navigation event
    private fun onRecipeClick(recipeId: Int) {
        viewModelScope.launch {
            _event.emit(
                SavedRecipesEvent.NavigateToRecipeDetail(recipeId)
            )
        }
    }

    // snack bar
    private fun emitReachedBottomSnackBar() {
        viewModelScope.launch {
            _event.emit(
                SavedRecipesEvent.ShowSnackBar(
                    message = "모든 저장된 레시피를 확인했습니다."
                )
            )
        }
    }

    // data load
    private fun loadSavedRecipes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val result = getSavedRecipesUseCase.execute()) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            recipes = result.data,
                            bookmarkedIds = result.data.map { it.id }.toSet(),
                            isLoading = false
                        )
                    }
                }

                is Result.Error -> {
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    // bookmark
    private fun onBookmarkClick(recipeId: Int) {
        viewModelScope.launch {
            when (val result = toggleBookmarkUseCase.execute(recipeId)) {
                is Result.Success -> {
                    val isBookmarked = result.data
                    _state.update { state ->
                        state.copy(
                            bookmarkedIds = if (isBookmarked) {
                                state.bookmarkedIds + recipeId
                            } else {
                                state.bookmarkedIds - recipeId
                            }
                        )
                    }
                }

                is Result.Error -> {
                    // TODO: error handling
                }
            }
        }
    }
}