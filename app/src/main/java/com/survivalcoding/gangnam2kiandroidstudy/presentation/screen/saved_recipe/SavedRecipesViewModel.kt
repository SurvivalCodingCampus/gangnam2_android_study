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
        println("SavedRecipesViewModel init")
        viewModelScope.launch {
            getSavedRecipesUseCase().collect { recipes ->
                _state.update { it.copy(savedRecipes = recipes) }
            }
        }
    }

    suspend fun saveNewRecipe(id: Long) {
        try {
            addBookmarkUseCase(id)
        } catch (e: Exception) {
            println("저장 실패: $e")
        }
    }
    
    fun removeBookmark(recipeId: Long) {
        viewModelScope.launch {
            try {
                removeBookmarkUseCase(recipeId)
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