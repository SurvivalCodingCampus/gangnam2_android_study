package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun SavedRecipesRoot(
    modifier: Modifier = Modifier,
    viewModel: SavedRecipesViewModel = hiltViewModel(),
    onNavigateToRecipeDetail: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is SavedRecipesEvent.NavigateToRecipeDetail -> {
                    onNavigateToRecipeDetail(event.recipeId)
                }

                is SavedRecipesEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

            }
        }
    }

    Scaffold(
        containerColor = AppColors.white,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        SavedRecipesScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 30.dp),
            state = state,
            onAction = viewModel::onAction,
        )
    }
}
