package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.data.saved_recipes.SavedRecipesDao
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipesRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchRecipesViewModel(
    private val repository: RecipesRepository,
    private val dao: SavedRecipesDao
) : ViewModel() {
    private var _state = MutableStateFlow(SearchRecipesState())
    private var cachedRecipes: List<Recipe> = emptyList()
    private var savedRecipesId: List<Int> = emptyList()
    val state = _state.asStateFlow()

    private var _event = MutableSharedFlow<SearchRecipesEvent>()
    val event = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            cachedRecipes = repository.getAllRecipes()
            savedRecipesId = dao.getSavedRecipesList().map { it.recipeId }
            _state.value = _state.value.copy(resultRecipes = cachedRecipes.filter {
                savedRecipesId.contains(it.id)
            })
        }
    }

    fun toggleBottomSheet() {
        _state.value = _state.value.copy(enableBottomSheet = !_state.value.enableBottomSheet)
    }

    fun filterRecipes(
        searchText: String = "", time: String = "", rate: String = "", category: String = ""
    ) {
        viewModelScope.launch {
            var filteredList = if (searchText.isNotEmpty()) {
                cachedRecipes.filter { it.name.contains(searchText, ignoreCase = true) }
            } else {
                cachedRecipes.filter {
                    savedRecipesId.contains(it.id)
                }
            }
            // Rating 필터
            if (rate.isNotEmpty()) {
                val ratingThreshold = rate.toIntOrNull()
                if (ratingThreshold != null) {
                    filteredList = filteredList.filter { it.rating >= ratingThreshold }
                }
            }

            // Category 필터
            if (category.isNotEmpty()) {
                filteredList = filteredList.filter { it.category == category }
            }

            // Time 정렬
            if (time.isNotEmpty()) {
                filteredList = filteredList.sortedBy { it.time }
            }
            _state.value = _state.value.copy(
                searchInputText = searchText,
                selectedTime = time,
                selectedRate = rate,
                selectedCategory = category,
                resultRecipes = filteredList,
                enableBottomSheet = false
            )
        }
    }

    fun applyFilterRecipe(
        searchText: String = "", time: String = "", rate: String = "", category: String = ""
    ) {
        viewModelScope.launch {
            filterRecipes(searchText, time, rate, category)
            _event.emit(SearchRecipesEvent.ShowSnackBar("필터가 적용되었습니다."))
        }
    }

    fun dismissFilterRecipe() {
        viewModelScope.launch {
            _event.emit(SearchRecipesEvent.ShowSnackBar("필터가 취소되었습니다."))
            _state.value = _state.value.copy(enableBottomSheet = false)
        }
    }
}