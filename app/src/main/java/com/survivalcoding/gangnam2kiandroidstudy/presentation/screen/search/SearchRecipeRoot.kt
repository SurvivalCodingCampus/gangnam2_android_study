package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchRecipeRoot(
    navigateToDetail: (recipeId: Int) -> Unit = {},
    viewModel: SearchRecipeViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SearchRecipeScreen(
        state = state,
        navigateToDetail = navigateToDetail,
        onAction = viewModel::onAction
    )
}
