package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = koinViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchRecipesScreen(
        state = state,
        onSearchRecipes = { query -> viewModel.searchRecipes(query) },
        onTapFilterButton = viewModel::tapFilterButton,
        onUpdateFilterSearchState = viewModel::updateFilterSearchState,
    )
}