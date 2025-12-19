package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchRecipesViewModel(
    private val repository: SavedRecipesRepository
) : ViewModel() {
    private var _state = MutableStateFlow(SearchRecipesState())
    private var cachedRecipes: List<Recipe> = emptyList()
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            cachedRecipes = repository.getSavedRecipes()
            _state.value = _state.value.copy(resultRecipes = cachedRecipes)
        }
    }

    fun toggleBottomSheet() {
        _state.value = _state.value.copy(enableBottomSheet = !_state.value.enableBottomSheet)
        Log.d("SearchRecipesViewModel", "toggleBottomSheet: ${_state.value.enableBottomSheet}")

    }


    fun filterRecipes(
        searchText: String = "",
        time: String = "",
        rate: String = "",
        category: String = ""
    ) {
        viewModelScope.launch {
            Log.d("SearchRecipesViewModel", "time: $time, rate: $rate, category: $category")


            var filteredList = if (searchText.isNotEmpty()) {
                cachedRecipes.filter { it.name.contains(searchText, ignoreCase = true) }
            } else {
                cachedRecipes
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
            Log.d("SearchRecipesViewModel", "result: ${_state.value}")
        }
    }

}