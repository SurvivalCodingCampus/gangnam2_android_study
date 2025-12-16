package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import androidx.lifecycle.ViewModel
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepository

class RecipeDetailViewModel(
    private val ingredientRepository: IngredientRepository,
    private val procedureRepository: ProcedureRepository // GetRecipeDetailsUseCase 는 추후에 리팩토링으로 작성
) : ViewModel() {
}