package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.RecipeAppApplication
import com.survivalcoding.gangnam2kiandroidstudy.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.repository.SavedRecipesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchRecipesViewModel(
    private val savedRecipesRepository: SavedRecipesRepository
) : ViewModel() {
    private var _state = MutableStateFlow(SearchRecipesState())
    private var cachedRecipes: List<Recipe> = emptyList()
    val state = _state.asStateFlow().debounce(1000)

    init {
        viewModelScope.launch {
            val resultList: List<Recipe> =
                savedRecipesRepository.getSavedRecipes()
            _state.value = _state.value.copy(resultRecipes = resultList)
            cachedRecipes = savedRecipesRepository.getSavedRecipes()

        }


    }

    fun changeSearchInputText(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (searchText.isEmpty()) {
                _state.value =
                    _state.value.copy(
                        resultRecipes = cachedRecipes,
                        searchInputText = "",
                    )
            } else {
                _state.value =
                    _state.value.copy(
                        resultRecipes = cachedRecipes.filter {
                            it.name.contains(
                                searchText
                            )
                        },
                        searchInputText = searchText,
                    )
            }

        }


    }

    fun filterRecipes(time: String, rate: String, category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("SearchRecipesViewModel", "time: $time, rate: $rate, category: $category")

            _state.value = _state.value.copy(
                selectedTime = time,
                selectedRate = rate,
                selectedCategory = category,
                resultRecipes =
                    if (rate.isNotEmpty()) {

                        if (category.isNotEmpty()) {
                            cachedRecipes.filter { recipe ->
                                recipe.rating >= rate.toInt()
                            }.filter { recipe -> recipe.category == category }
                        } else {
                            cachedRecipes.filter { recipe ->
                                recipe.rating >= rate.toInt()
                            }
                        }


                    } else {
                        if (category.isNotEmpty()) {
                            cachedRecipes
                                .filter { recipe -> recipe.category == category }
                        } else {
                            cachedRecipes
                        }
                    }

            )
            Log.d("SearchRecipesViewModel", "result: ${_state.value}")
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedRecipeRepository =
                    (this[APPLICATION_KEY] as RecipeAppApplication).savedRecipesRepository
                SearchRecipesViewModel(
                    savedRecipesRepository = savedRecipeRepository,
                )
            }
        }
    }
}