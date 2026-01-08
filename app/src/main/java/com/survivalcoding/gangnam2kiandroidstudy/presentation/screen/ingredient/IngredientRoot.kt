package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeLinkDialog

@Composable
fun IngredientRoot(
    recipeId: Long,
    onBackClick: () -> Unit,
    viewModel: IngredientViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(recipeId) {
        viewModel.loadRecipeDetail(recipeId)
        viewModel.loadProcedure(recipeId)
    }

    IngredientScreen(
        state = state,
        onAction = { action ->
            if (action is IngredientAction.OnBackClick) {
                onBackClick()
            } else {
                viewModel.onAction(action)
            }
        }
    )

    val linkText = "app.foodrecipe.com/recipe143"

    if (state.isShareDialogVisible) {
        RecipeLinkDialog(
            linkText = linkText,
            onDismissRequest = { viewModel.onAction(IngredientAction.OnDismissShareDialog) },
            onCopyClick = {
                viewModel.copyLink(linkText)
                viewModel.onAction(IngredientAction.OnDismissShareDialog)
            }
        )
    }
}