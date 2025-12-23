package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchRecipesRoot(viewModel: SearchRecipesViewModel = koinViewModel()) {
    val searchRecipesState by viewModel.state.collectAsState(SearchRecipesState())
    val snackBarHostState = remember { SnackbarHostState() }
    Log.d("SearchRecipesRoot", "searchRecipes: $searchRecipesState")

    Scaffold( //스낵바 정의
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) {
                Snackbar() {
                    Text(text = it.visuals.message)
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            SearchRecipesScreen(
                state = searchRecipesState,
                onAction = { action ->
                    when (action) {
                        is SearchRecipesAction.ToggleBottomSheet -> viewModel.toggleBottomSheet()
                        is SearchRecipesAction.ApplyFilterRecipes -> {
                            Log.d("SearchRecipesRoot", "SearchRecipesRoot: 진입")
                            viewModel.applyFilterRecipe(
                                searchText = action.searchText,
                                time = action.time,
                                rate = action.rate,
                                category = action.category
                            )
                        }

                        is SearchRecipesAction.CancelFilterRecipes -> {
                            viewModel.dismissFilterRecipe()
                        }

                        is SearchRecipesAction.ChangeSearch -> {
                            viewModel.filterRecipes(
                                searchText = action.searchText,
                                time = action.time,
                                rate = action.rate,
                                category = action.category
                            )
                        }
                    }
                },
            )
        }
    }

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is SearchRecipesEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.str)
                }
            }
        }
    }
}