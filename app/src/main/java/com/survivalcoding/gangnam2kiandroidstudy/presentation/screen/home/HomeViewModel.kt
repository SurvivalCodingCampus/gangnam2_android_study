package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.ToggleBookmarkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.selector.HomeCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel(
    private val recipeRepository: RecipeRepository,
    private val bookmarkRepository: BookmarkRepository,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        loadRecipes()
    }

    // 전체 로드
    private fun loadRecipes() {
        viewModelScope.launch {
            val recipes = recipeRepository.getRecipes()

            val bookmarkedIds = bookmarkRepository
                .getSavedRecipeIds()
                .toSet()

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
                    bookmarkedIds = bookmarkedIds
                )


            }
        }
    }


    // 카테고리 선택
    fun onSelectCategory(category: HomeCategory) {
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
    fun onBookmarkClick(recipeId: Int) {
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


    companion object {
        fun Factory(application: AppApplication): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    HomeViewModel(
                        recipeRepository = application.recipeRepository,
                        bookmarkRepository = application.bookmarkRepository,
                        toggleBookmarkUseCase = application.toggleBookmarkUseCase
                    )
                }
            }
    }
}