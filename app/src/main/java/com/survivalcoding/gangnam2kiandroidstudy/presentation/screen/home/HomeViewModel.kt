package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.toCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.toCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.AddBookmarkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetBookmarkedRecipeIdsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.RemoveBookmarkUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getBookmarkedRecipeIdsUseCase: GetBookmarkedRecipeIdsUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnBookmarkClick -> {
                toggleBookmark(action.recipeId)
            }

            is HomeAction.OnCategoryClick -> {
                onSelectCategory(action.category)
            }

            HomeAction.OnProfileClick -> {
                viewModelScope.launch {
                    _event.emit(HomeEvent.NavigateToProfile)
                }
            }

            HomeAction.OnSearchClick -> {
                viewModelScope.launch {
                    _event.emit(HomeEvent.NavigateToSearch)
                }
            }

            is HomeAction.OnDishClick -> {
                viewModelScope.launch {
                    _event.emit(HomeEvent.NavigateToRecipeDetail(action.recipeId))
                }
            }

            is HomeAction.OnNewRecipeClick -> {
                viewModelScope.launch {
                    _event.emit(HomeEvent.NavigateToRecipeDetail(action.recipeId))
                }
            }
        }
    }

    init {
        println("MainViewModel init")
        
        // 즐겨찾기 실시간 구독
        viewModelScope.launch {
            getBookmarkedRecipeIdsUseCase().collect { savedIds ->
                _state.update { it.copy(savedRecipeIds = savedIds) }
            }
        }

        loadData()
    }

    fun loadData() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            _state.update { it.copy(isLoading = true, isError = false) }

            // Load Recipes
            val recipesResult = getRecipesUseCase.execute()
            
            if (recipesResult is Result.Success) {
                val all = recipesResult.data

                _state.update { currentState ->
                    currentState.copy(
                        allRecipes = all,
                        selectedRecipes = if (currentState.selectedCategory.toCategory() == RecipeCategory.ALL) {
                            all
                        } else if (currentState.selectedCategory.toCategory() == RecipeCategory.NONE) {
                            emptyList()
                        } else {
                            all.filter { recipe ->
                                recipe.category == currentState.selectedCategory.toCategory()
                            }
                        },
                        isLoading = false,
                    )
                }
            } else {
                println("에러 처리")
                _state.update { it.copy(isLoading = false, isError = true) }
            }
            
            getNewRecipesTop5()
        }
    }

    // 카테고리 선택에 따라 선택된 레시피 리스트 업데이트
    private fun onSelectCategory(category: String) {
        _state.update { it.copy(selectedCategory = category) }
        filterRecipesByCategory(category.toCategory())
    }

    private fun filterRecipesByCategory(category: RecipeCategory) {
        val all = state.value.allRecipes

        val filtered =
            if (category == RecipeCategory.ALL) {
                all
            } else if (category == RecipeCategory.NONE) {
                emptyList()
            } else {
                all.filter { recipe ->
                    recipe.category == category
                }
            }

        _state.update { it.copy(selectedRecipes = filtered) }
    }


    // 모든 레시피 읽어오기
    // race condition 방지
    private var loadJob: Job? = null

    // 레시피 저장
    private fun toggleBookmark(recipeId: Long) {
        viewModelScope.launch {
            val isBookmarked = recipeId in state.value.savedRecipeIds
            
            // 실제 DB 업데이트 (Flow를 통해 UI 업데이트됨)
            try {
                if (isBookmarked) {
                    removeBookmarkUseCase(recipeId)
                } else {
                    addBookmarkUseCase(recipeId)
                }
            } catch (e: Exception) {
                println("즐겨찾기 추가 실패: $e")
            }
        }
    }

    // 최신 레시피 상위 5개 저장
    private fun getNewRecipesTop5() {
        val all = state.value.allRecipes

        _state.update {
            it.copy(
                newRecipes = all.sortedByDescending { it.id }.take(5)
            )
        }
    }

    // 파괴될 때
    override fun onCleared() {
        println("MainViewModel cleared")
        super.onCleared()
    }
}