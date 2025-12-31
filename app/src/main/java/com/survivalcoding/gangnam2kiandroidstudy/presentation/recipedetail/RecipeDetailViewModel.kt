package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.CopyLinkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val copyLinkUseCase: CopyLinkUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _event = MutableSharedFlow<RecipeDetailEvent>()
    val event = _event.asSharedFlow()

    private val _uiState = MutableStateFlow(RecipeDetailUiState())
    val uiState = _uiState.asStateFlow()

    val recipeId = savedStateHandle.get<Int>("recipeId") ?: -1

    private fun loadRecipeDetail(recipeId: Int) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getRecipeDetailsUseCase(recipeId)
                .onSuccess { (pair, ingredient, procedure) ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            recipe = pair.first,
                            ingredients = ingredient,
                            procedures = procedure,
                            chef = pair.second
                        )
                    }
                }
                .onFailure {
                    _uiState.update { state -> state.copy(isLoading = false, message = it.message) }
                }
        }
    }

    private fun followChef(chefId: Int) {
        viewModelScope.launch {
            emitEvent(RecipeDetailEvent.FollowCompleted(chefId))
        }
    }

    private fun emitEvent(event: RecipeDetailEvent) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun onAction(action: RecipeDetailAction) {
        when (action) {
            is RecipeDetailAction.TabClick -> {
                _uiState.update { it.copy(selectedTabPosition = action.position) }
            }
            is RecipeDetailAction.FollowClick -> {
                followChef(action.chefId)
            }
            RecipeDetailAction.BackClick -> {
                emitEvent(RecipeDetailEvent.NavigateUp)
            }
            is RecipeDetailAction.MenuClick -> {
                _uiState.update {
                    it.copy(isShowMenu = action.isShow)
                }
            }
            RecipeDetailAction.ShareClick -> {
                _uiState.update { it.copy(isShowShareDialog = true) }
            }
            RecipeDetailAction.DismissShareDialog -> {
                _uiState.update { it.copy(isShowShareDialog = false) }
            }
            RecipeDetailAction.CopyLinkClick -> {
                val link = "app.Recipe.co/${uiState.value.recipe.id}"
                val success = copyLinkUseCase(link)

                emitEvent(
                    RecipeDetailEvent.ShowMessage(
                        if (success) "클립보드에 복사되었습니다."
                        else "실패 했습니다."
                    )
                )
                _uiState.update {
                    it.copy(isShowShareDialog = false)
                }
            }
        }
    }

    init {
        loadRecipeDetail(recipeId)
    }
}