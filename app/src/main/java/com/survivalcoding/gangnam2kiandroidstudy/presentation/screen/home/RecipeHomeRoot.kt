package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RecipeHomeRoot(
    navigateToSearchRecipe: () -> Unit = {},
    navigateToDetail: (recipeId: Int) -> Unit = {},
    viewModel: RecipeHomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RecipeHomeScreen(
        state = state,
        navigateToSearchRecipe = navigateToSearchRecipe,
        navigateToDetail = navigateToDetail,
        onAction = viewModel::onAction
    )
}
