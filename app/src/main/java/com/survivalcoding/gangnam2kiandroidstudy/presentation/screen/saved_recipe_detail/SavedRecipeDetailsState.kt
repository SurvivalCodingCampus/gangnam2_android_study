package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredients
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure

data class SavedRecipeDetailsState(
    val isSelectIngredient: Boolean = true,
    val procedureList: List<Procedure> = emptyList(),
    val ingredientList: List<Ingredients> = emptyList(),
    val isDropDownMenuShow: Boolean = false,
    val isShareDialogShow: Boolean = false
)