package com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun IngredientScreenRoot(
    recipeId: Int,
    viewModel: IngridentViewModel = koinViewModel(),
) {
    viewModel.loadRecipeDetail(recipeId)

    val state = viewModel.state.collectAsState()

    IngredientScreen(
        state = state.value,
        onValueChange = { index ->
            viewModel.toggleTab(index)
        }
    )
}