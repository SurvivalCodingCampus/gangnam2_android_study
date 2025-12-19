package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRecipeScreenRoot(
    viewModel: SearchRecipesViewModel = koinViewModel(),
    onRecipeClick: (Int) -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is SearchRecipesEvent.OnSelectRecipes -> {
                    onRecipeClick(event.id)
                }

                is SearchRecipesEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppColors.white,
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        SearchRecipeScreen(
            modifier = Modifier.padding(innerPadding),
            state = state,
            onAction = viewModel::onAction
        )
    }

    if (state.showBottomSheet) {
        FilterSearchBottomSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            currentFilterState = state.filterState,
            onDismiss = {
                viewModel.onAction(SearchRecipesAction.OnTapFilterButton)
            },
            onApplyFilter = {
                viewModel.onAction(SearchRecipesAction.OnUpdateFilterSearchState(it))
                viewModel.onAction(SearchRecipesAction.OnTapFilterButton)
            },
            onCancelFilter = {
                viewModel.onAction(SearchRecipesAction.OnTapFilterButton)
                viewModel.onAction(SearchRecipesAction.OnCancelFilter)
            }

        )
    }
}