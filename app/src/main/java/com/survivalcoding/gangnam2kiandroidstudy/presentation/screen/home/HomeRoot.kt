package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoot(
    viewModel: HomeViewModel = koinViewModel(),
    onSearchClicked: () -> Unit,
    onRecipeItemClicked: (Recipe) -> Unit
) {
    val homeState by viewModel.state.collectAsState()
    HomeScreen(
        homeState,
        onAction = { action ->
            when (action) {
                is HomeAction.OnSearchClicked -> onSearchClicked()
                is HomeAction.OnSelectedCategory -> viewModel.onAction(action)
                is HomeAction.OnRecipeItemClicked -> onRecipeItemClicked(action.recipe)//onAction(HomeAction.OnRecipeItemClicked(action.recipe))
            }
        },
        onAddSavedRecipe = { viewModel.addSavedRecipe(it.id) },
        onDeleteSavedRecipe = {
            viewModel.deleteSavedRecipe(it.id)

        }
    )
}