package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.CopyLinkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val copyLinkUseCase: CopyLinkUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RecipeDetailState())
    val state = _state.asStateFlow()

    fun loadRecipeDetail(recipeId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val details = getRecipeDetailsUseCase(recipeId)

            if (details == null) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        message = "Recipe not found"
                    )
                }
                return@launch
            }
            _state.update {
                it.copy(
                    isLoading = false,
                    recipe = details.recipe,
                    ingredients = details.ingredients,
                    procedures = details.procedures,
                )
            }
        }
    }

    // 액션
    fun onAction(action: RecipeDetailAction) {
        when (action) {

            /* 탭 전환 */
            is RecipeDetailAction.OnValueChange -> {
                _state.update { it.copy(selectedIndex = action.index) }
            }

            // 드롭다운
            RecipeDetailAction.OnDropDownClick -> {
                _state.update { it.copy(isDropDownExpanded = true) }
            }

            RecipeDetailAction.OnDropDownDismiss -> {
                _state.update { it.copy(isDropDownExpanded = false) }
            }

            // 쉐어
            RecipeDetailAction.OnShareClick -> {
                _state.update {
                    it.copy(
                        isDropDownExpanded = false,
                        isShareDialogVisible = true,
                        shareLink = buildShareLink(it.recipe?.id)
                    )
                }
            }

            RecipeDetailAction.OnShareDialogDismiss -> {
                _state.update { it.copy(isShareDialogVisible = false) }
            }

            is RecipeDetailAction.OnCopyLinkClick -> {
                copyLinkUseCase(action.link)
                _state.update {
                    it.copy(
                        isShareDialogVisible = false,
                        message = "링크가 클립보드에 복사되었습니다."
                    )
                }
            }

            // 백 (네비게이션은 Root에서 처리)
            RecipeDetailAction.OnArrowBackClick -> Unit
            RecipeDetailAction.OnMessageShown -> userMessageShown()
        }
    }

    fun userMessageShown() {
        _state.update { it.copy(message = null) }
    }

    private fun buildShareLink(recipeId: Int?): String {
        return recipeId?.let {
            "https://recipe.app/recipe/$it"
        } ?: ""
    }
}
