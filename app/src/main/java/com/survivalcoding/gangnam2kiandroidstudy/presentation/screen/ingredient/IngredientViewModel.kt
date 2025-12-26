package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(IngredientState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<IngredientEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: IngredientAction) {
        when (action) {
            is IngredientAction.SelectTab -> onTabSelected(action.index)
            is IngredientAction.BackClicked -> onBackClicked()
            IngredientAction.FollowClicked -> onFollowClicked()
            IngredientAction.ShareClicked -> onShareClicked()
            IngredientAction.RateClicked -> onRateClicked()
            IngredientAction.ReviewClicked -> onReviewClicked()
            IngredientAction.UnsaveClicked -> onUnsaveClicked()
        }
    }


    fun loadIngredients(recipeId: Int) {
        viewModelScope.launch {
            val detail = getRecipeDetailUseCase.execute(recipeId) ?: return@launch

            _state.update {
                it.copy(
                    recipeDetail = detail
                )
            }
        }
    }

    private fun onBackClicked() {
        viewModelScope.launch {
            _event.emit(
                IngredientEvent.NavigateBack
            )
        }
    }

    private fun onTabSelected(index: Int) {
        _state.update {
            it.copy(selectedTab = index)
        }
    }

    private fun onFollowClicked() {
        // Todo: 추후 구현
    }

    private fun onShareClicked() {
        viewModelScope.launch {
            // Event로 다이얼로그 열기 요청
            _event.emit(IngredientEvent.ShowShareDialog)
        }
    }

    private fun onRateClicked() {
        // Todo: 추후 구현
    }

    private fun onReviewClicked() {
        // Todo: 추후 구현
    }

    private fun onUnsaveClicked() {
        // Todo: 추후 구현
    }

}