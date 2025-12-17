package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.di.DependencyContainer

@Composable
fun SavedRecipesRoot(
    viewModel: SavedRecipesViewModel = viewModel(
        factory = DependencyContainer.provideSavedRecipesViewModelFactory(LocalContext.current)
    ),
    onRecipeClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SavedRecipesScreen(uiState, onRecipeClick, viewModel::removeBookmark)
}