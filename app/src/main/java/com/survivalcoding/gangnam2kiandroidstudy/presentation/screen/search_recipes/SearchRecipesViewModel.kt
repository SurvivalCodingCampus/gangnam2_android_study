package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

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
    val state = _state.asStateFlow().debounce(1000)

    init {
        viewModelScope.launch {
            val resultList: List<Recipe> =
                savedRecipesRepository.getSavedRecipes()
            _state.value = _state.value.copy(resultRecipes = resultList)
        }


    }

    fun changeSearchInputText(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (searchText.isEmpty()) {
                _state.value =
                    _state.value.copy(
                        resultRecipes = savedRecipesRepository.getSavedRecipes(),
                        searchInputText = "",
                    )
            }
            _state.value =
                _state.value.copy(
                    resultRecipes = savedRecipesRepository.getSavedRecipes().filter {
                        it.name.contains(
                            searchText
                        )
                    },
                    searchInputText = searchText,
                )
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