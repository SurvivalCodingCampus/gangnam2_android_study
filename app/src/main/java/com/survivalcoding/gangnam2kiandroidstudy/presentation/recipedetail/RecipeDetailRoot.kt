package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ShareRecipeDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeDetailRoot(
    viewModel: RecipeDetailViewModel = koinViewModel(),
    onNavigateUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                RecipeDetailEvent.NavigateUp -> onNavigateUp()
                is RecipeDetailEvent.ShowMessage -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                else -> Unit
            }
        }
    }
    RecipeDetailScreen(
        recipeDetailUiState = uiState,
        onAction = viewModel::onAction
    )
    if (uiState.isShowShareDialog) {
        ShareRecipeDialog(
            recipeLink = "app.Recipe.co/${uiState.recipe.name}",
            onDismiss = {
                viewModel.onAction(RecipeDetailAction.DismissShareDialog)
            },
            onCopyClick = {
                viewModel.onAction(RecipeDetailAction.CopyLinkClick)
            }
        )
    }
}