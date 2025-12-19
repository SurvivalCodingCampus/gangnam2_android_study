package com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchRecipesRoot(
    onBack: () -> Unit,
    viewModel: SearchRecipesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is SearchRecipesEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is SearchRecipesEvent.GoBack -> onBack()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        SearchRecipesScreen(
            state = state,
            onAction = viewModel::onAction,
            paddingValues = it,
        )
    }
}
