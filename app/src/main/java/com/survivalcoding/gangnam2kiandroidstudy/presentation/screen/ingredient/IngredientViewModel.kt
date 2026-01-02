package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.AddBookmarkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.CopyLinkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetBookmarkedRecipeIdsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeProcedureUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.RemoveBookmarkUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val getRecipeProcedureUseCase: GetRecipeProcedureUseCase,
    private val copyLinkUseCase: CopyLinkUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
    private val getBookmarkedRecipeIdsUseCase: GetBookmarkedRecipeIdsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(IngredientState())
    val state: StateFlow<IngredientState> = _state.asStateFlow()

    init {

    }

    fun loadRecipeDetail(recipeId: Long) {
        viewModelScope.launch {
            // 레시피 상세 정보 로드
            when (val response = getRecipeDetailsUseCase.execute(recipeId)) {
                is Result.Success -> _state.update { it.copy(recipe = response.data) }

                is Result.Error -> println("에러 처리")
            }
            
            // 북마크 상태 확인
            when (val bookmarksResult = getBookmarkedRecipeIdsUseCase.execute()) {
                is Result.Success -> {
                    val isBookmarked = recipeId in bookmarksResult.data
                    _state.update { it.copy(isBookmarked = isBookmarked) }
                }
                is Result.Error -> println("북마크 로드 실패")
            }
        }
    }

    // tap 클릭마다 index 업데이트
    fun updateTabIndex(index: Int) {
        _state.update { it.copy(tabIndex = index) }
    }

    // 과정 로드
    fun loadProcedure(recipeId: Long) {
        viewModelScope.launch {
            when (val result = getRecipeProcedureUseCase.execute(recipeId)) {
                is Result.Success -> _state.update { it.copy(procedures = result.data) }
                is Result.Error -> println("에러 처리")
            }
        }
    }

    fun copyLink(link: String) {
        copyLinkUseCase(link)
    }

    fun toggleBookmark() {
        val recipeId = state.value.recipe.id
        if (recipeId == 0L) return

        viewModelScope.launch {
            val isBookmarked = state.value.isBookmarked
            
            // 낙관적 업데이트
            _state.update { it.copy(isBookmarked = !isBookmarked) }

            try {
                if (isBookmarked) {
                    removeBookmarkUseCase(recipeId)
                } else {
                    addBookmarkUseCase(recipeId)
                }
            } catch (e: Exception) {
                // 롤백
                _state.update { it.copy(isBookmarked = isBookmarked) }
                println("북마크 토글 실패")
            }
        }
    }
}