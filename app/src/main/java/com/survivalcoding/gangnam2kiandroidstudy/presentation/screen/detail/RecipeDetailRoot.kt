package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeDetailRoot(
    recipeId: Int,
    onBack: () -> Unit,
    viewModel: RecipeDetailViewModel = koinViewModel()
) {
    LaunchedEffect(recipeId) {
        viewModel.loadRecipeDetail(recipeId)
    }
    val state = viewModel.state.collectAsStateWithLifecycle()

    RecipeDetailScreen(
        state = state.value,
        onAction = viewModel::onAction,
        onBackClick = onBack
    )
}
