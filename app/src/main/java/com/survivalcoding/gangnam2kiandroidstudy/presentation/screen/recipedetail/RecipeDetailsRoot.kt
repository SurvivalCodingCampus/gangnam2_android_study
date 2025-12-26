package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ShareDialog
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RecipeDetailsRoot(
    id: Long,
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailsViewModel = koinViewModel { parametersOf(id) },
    onNavigate: (RecipeDetailsNavigation) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                RecipeDetailsEvent.NavigateToBack -> onNavigate(RecipeDetailsNavigation.Back)
                is RecipeDetailsEvent.NavigateToReviews -> onNavigate(
                    RecipeDetailsNavigation.Reviews(
                        event.recipeId,
                    ),
                )
            }
        }
    }

    RecipeDetailsScreen(
        modifier = modifier,
        uiState = uiState,
        onAction = viewModel::onAction,
    )

    ShareDialog(
        isVisible = uiState.isShareDialogVisible,
        shareUrl = uiState.recipe?.shareUrl ?: "",
        onDismissRequest = {
            viewModel.onAction(RecipeDetailsAction.OnShareDismissRequest)
        },
        onCopy = {
            viewModel.onAction(RecipeDetailsAction.OnCopyClick(it))
        },
    )
}