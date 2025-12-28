package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipeItemRoot(
    recipeId: Int,
    viewModel: SavedRecipeDetailsViewModel = koinViewModel(),
    onBackButtonClick: () -> Unit
) {

    val state = viewModel.state.collectAsState()

    viewModel.init(recipeId)

    SavedRecipeItemScreen(
        state = state.value, onValueChanged = {
            viewModel.changeValue(it)
        }, procedure = state.value.procedureList,
        onBackButtonClicked = { onBackButtonClick() },
        onMoreButtonClicked = { viewModel.toggleMenu(it) },
        onShareDialogRequest = { viewModel.toggleShareDialog(it) },
        onCopyLink = { viewModel.copyLink(it) }
    )

}

@Preview(showBackground = true)
@Composable
private fun SavedRecipeItemRootPreview() {
    //SavedRecipeItemRoot()

}