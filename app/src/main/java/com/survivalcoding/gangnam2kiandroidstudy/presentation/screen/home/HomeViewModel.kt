package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetNewRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.ToggleBookmarkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.selector.HomeCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val bookmarkRepository: BookmarkRepository,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
    private val getNewRecipesUseCase: GetNewRecipesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        loadHome()
    }

    // 전체 로드
    private fun loadHome() {
        // 전체
        viewModelScope.launch {
            val recipes = recipeRepository.getRecipes()

            val bookmarkedIds = bookmarkRepository
                .getSavedRecipeIds()
                .toSet()

            // new
            val newRecipes = getNewRecipesUseCase.execute()

            _state.update { current ->
                // 현재 선택된 카테고리를 유지하면서 셀렉터 다시 계산
                val filtered =
                    if (current.selectedCategory == HomeCategory.ALL) {
                        recipes
                    } else {
                        recipes.filter { it.category == current.selectedCategory.label }
                    }

                current.copy(
                    recipes = recipes,
                    filteredRecipes = filtered,  // 현재 선택 상태 기반으로 셀렉터 재적용
                    bookmarkedIds = bookmarkedIds,
                    newRecipes = newRecipes,
                )
            }
        }
    }

    private fun loadNewRecipes() {
        viewModelScope.launch {
            val newRecipes = getNewRecipesUseCase.execute()

            _state.update {
                it.copy(
                    newRecipes = newRecipes
                )
            }
        }
    }

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.SelectCategory -> {
                onSelectCategory(action.category)
            }

            is HomeAction.ToggleBookmark -> {
                onBookmarkClick(action.recipeId)
            }
        }
    }


    // 카테고리 선택
    private fun onSelectCategory(category: HomeCategory) {
        _state.update { current ->
            val filtered = if (category == HomeCategory.ALL) {
                current.recipes
            } else {
                current.recipes.filter { it.category == category.label }
            }

            current.copy(
                selectedCategory = category,
                filteredRecipes = filtered
            )
        }
    }

    // 북마크
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

                is Result.Error -> { /* 에러 처리 */
                }
            }
        }
    }
}