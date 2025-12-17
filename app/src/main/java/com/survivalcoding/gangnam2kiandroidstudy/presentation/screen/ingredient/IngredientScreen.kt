package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tab

@Composable
fun IngredientScreen(
    state: IngredientState,
) {
    Column() {
        Row() {

        }
        RecipeCard(
            recipe = state.recipe
        )

        // 프로필

        Tab(
            labels = listOf("Ingredient", "Procedure")
        )

        Row(

        ) { }

        LazyColumn(

        ) {
            items(state.recipe.ingredients) { ingredientAmount ->
                IngredientItem(
                    ingredient = ingredientAmount.ingredient,
                    quantity = "${ingredientAmount.amount}g",
                )
            }
        }
    }
}