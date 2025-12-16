package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository,
    private val chefRepository: ChefRepository
    // private val procedureRepository: ProcedureRepository
) : ViewModel() {
    private val _state = MutableStateFlow(IngredientState())
    val state = _state.asStateFlow()

    fun loadIngredients(recipeId: Int) {
        viewModelScope.launch {
            val recipe = recipeRepository.getRecipeById(recipeId)
            val ingredients = ingredientRepository.getIngredientsByRecipeId(recipeId)
            val chef = recipe?.chefId?.let { chefId ->
                chefRepository.getChefById(chefId)
            }

            _state.update {
                it.copy(
                    recipe = recipe,
                    author = chef,
                    ingredients = ingredients
                )
            }
        }
    }

    fun onTabSelected(index: Int) {
        _state.update {
            it.copy(selectedTab = index)
        }
    }

    companion object {
        fun factory(application: AppApplication): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    IngredientViewModel(
                        recipeRepository = application.recipeRepository,
                        ingredientRepository = application.ingredientRepository,
                        chefRepository = application.chefRepository
                    )
                }
            }
    }

}