package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun RecipeDetailRoot(
    viewModel: RecipeDetailViewModel,
    onNavigateUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RecipeDetailScreen(
        recipeDetailUiState = uiState,
        onTabClick = viewModel::onTabClick,
        onFollowClick = {},
        onNavigateUp = onNavigateUp
    )
}