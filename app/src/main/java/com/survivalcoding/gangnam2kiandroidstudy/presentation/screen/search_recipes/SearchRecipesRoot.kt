package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.appbar.CustomAppTopBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.FilterSearchBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppColors

@Composable
fun SearchRecipesRoot(
    modifier: Modifier = Modifier,
    viewModel: SearchRecipesViewModel = hiltViewModel(),
    onNavigateToRecipeDetail: (Int) -> Unit,
    onBack: () -> Unit,

    ) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is SearchRecipesEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(event.message)
                }

                is SearchRecipesEvent.NavigateToRecipeDetail -> {
                    onNavigateToRecipeDetail(event.recipeId)
                }

                SearchRecipesEvent.NavigateBack -> {
                    onBack()
                }
            }
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = AppColors.white,
        topBar = {
            CustomAppTopBar(
                text = stringResource(R.string.search_recipes_title),
                showBackButton = true,
                onBackClick = onBack
            )
        }

    ) { innerpadding ->
        SearchRecipesScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(horizontal = 30.dp),
            state = state,
            onAction = viewModel::onAction,
            onRecipeClick = viewModel::onRecipeClick
        )
    }

    // filter bottom sheet
    if (state.showBottomSheet) {
        FilterSearchBottomSheet(
            initialState = state.filterState,
            onDismiss = {
                viewModel.onAction(SearchRecipesAction.FilterDismissed)
            },
            onApplyFilter = {
                viewModel.onAction(SearchRecipesAction.FilterApplied(it))
            }
        )
    }

}
