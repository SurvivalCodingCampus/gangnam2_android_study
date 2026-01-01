package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.AddBookmarkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.RemoveBookmarkUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SavedRecipesState())
    val state: StateFlow<SavedRecipesState> = _state.asStateFlow()

    init {
        println("MainViewModel init")
        viewModelScope.launch {
            loadRecipes()
        }
    }

    suspend fun loadRecipes() {
        when (val response = getSavedRecipesUseCase.execute()) {
            is Result.Success -> _state.update {
                it.copy(savedRecipes = response.data)
            }

            is Result.Error -> println("에러 처리")
        }
    }

    suspend fun saveNewRecipe(id: Long) {
        // DB에 저장
        try {
            addBookmarkUseCase(id)
        } catch (e: Exception) {
            println("저장 실패: $e")
            return
        }

        // UI 업데이트
        when (val response = getRecipeDetailsUseCase.execute(id)) {
            is Result.Success -> _state.update { currentState ->
                // 이미 저장된 레시피면 그대로
                if (currentState.savedRecipes.any { it.id == id }) {
                    currentState
                } else {
                    currentState.copy(
                        savedRecipes = currentState.savedRecipes + response.data
                    )
                }
            }

            is Result.Error -> println("에러 처리")
        }
    }
    
    fun removeBookmark(recipeId: Long) {
        viewModelScope.launch {
            try {
                removeBookmarkUseCase(recipeId)
                // 목록에서 제거
                _state.update { state ->
                    state.copy(savedRecipes = state.savedRecipes.filter { it.id != recipeId })
                }
            } catch (e: Exception) {
                println("삭제 실패: $e")
            }
        }
    }

    // 파괴될 때
    override fun onCleared() {
        println("MainViewModel cleared")
        super.onCleared()
    }
}