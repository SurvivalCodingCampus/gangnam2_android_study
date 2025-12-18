package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeDetailsRoot(
    id: Long,
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailsViewModel = koinViewModel(),
    onBackClick: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.fetchRecipeDetails(id)
    }

    RecipeDetailsScreen(
        modifier = modifier,
        uiState = uiState,
        onTabClick = viewModel::changeTab,
        onBackClick = onBackClick,
    )
}