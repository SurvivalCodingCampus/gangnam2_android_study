package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipesRoot() {
    val viewModel: SavedRecipesViewModel = koinViewModel()

    val state = viewModel.state.collectAsState().value
    SavedRecipesScreen(
        state = state,
        onRefresh = viewModel::loadSavedRecipes,
        onRemoveBookmark = viewModel::removeBookmark
    )
}
