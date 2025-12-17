package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun RecipeDetailRoot(
    recipeId: Int,
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailViewModel = viewModel(
        factory = RecipeDetailViewModel.factory(
            LocalContext.current.applicationContext as AppApplication,
            recipeId
        )
    ),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    RecipeDetailScreen(
        uiState = uiState.value,
        modifier = modifier,
        onUpdateTabInfo = viewModel::updateTabInfo
    )
}
