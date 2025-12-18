package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipeItemRoot(
    recipe: Recipe,
    viewModel: SavedRecipeDetailsViewModel = koinViewModel()
) {

    val state = viewModel.state.collectAsState()

    viewModel.init(recipe.id)

    SavedRecipeItemScreen(
        state = state.value, recipe = recipe, onValueChanged = {
            viewModel.changeValue(it)
        }, procedure = state.value.procedureList
    )

}

@Preview(showBackground = true)
@Composable
private fun SavedRecipeItemRootPreview() {
    //SavedRecipeItemRoot()

}