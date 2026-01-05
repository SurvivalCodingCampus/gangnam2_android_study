package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail

import com.misterjerry.gangnam2kiandroidstudy.domain.model.Ingredients
import com.misterjerry.gangnam2kiandroidstudy.domain.model.Procedure
import com.misterjerry.gangnam2kiandroidstudy.domain.model.Recipe

data class SavedRecipeDetailsState(
    val isSelectIngredient: Boolean = true,
    val procedureList: List<Procedure> = emptyList(),
    val ingredientList: List<Ingredients> = emptyList(),
    val isDropDownMenuShow: Boolean = false,
    val isShareDialogShow: Boolean = false,
    val recipe: Recipe = Recipe(
        category = "",
        chef = "",
        id = 0,
        image = "",
        ingredients = emptyList(),
        name = "",
        rating = 0.0,
        time = "",
        false
    )
)