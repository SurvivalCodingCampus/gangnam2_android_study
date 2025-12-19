package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val recipeRepository: RecipeRepository,
    private val bookmarkRepository: BookmarkRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getAllRecipes()
        getBookmark()
    }


    fun onAction(action: HomeAction) {
        when(action) {
            is HomeAction.SearchRecipe -> {
                // navigate Search
            }
            is HomeAction.SelectCategory -> onSelectCategory(action.category)
            is HomeAction.BookmarkRecipe -> {
                bookmarkRecipe(action.id)
            }
            is HomeAction.SelectRecipe -> { }
        }
    }

    private fun onSelectCategory(category: String) {
        _state.update {
            it.copy(
                selectedCategory = category,
                filteredRecipes = if (category == "All") {
                    it.recipes
                } else {
                    it.recipes.filter { recipe ->
                        recipe.category == category
                    }
                }
            )
        }
    }

    fun getAllRecipes() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val recipes = recipeRepository.getRecipes()

            _state.update {
                it.copy(
                    isLoading = false,
                    recipes = recipes,
                    filteredRecipes = recipes
                )
            }

        }
    }

    fun getBookmark() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            val bookmarkIds = bookmarkRepository.getBookmarkId()
            _state.update {
                it.copy(
                    isLoading = false,
                    bookmarkIds = bookmarkIds
                )
            }
        }
    }

    private fun bookmarkRecipe(id: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            val list = _state.value.bookmarkIds
            if (id in list) {
                _state.update {
                    it.copy(
                        bookmarkIds = bookmarkRepository.removeBookmarkId(id),
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        bookmarkIds = bookmarkRepository.addBookmarkId(id),
                    )
                }
            }
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

}