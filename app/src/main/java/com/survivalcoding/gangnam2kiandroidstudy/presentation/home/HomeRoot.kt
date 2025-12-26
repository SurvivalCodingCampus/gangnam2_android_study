package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoot(
    onSearchClick: () -> Unit,
    onRecipeClick: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is HomeEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                HomeEvent.NavigateToSearch -> onSearchClick()
                is HomeEvent.NavigateToRecipeDetail -> onRecipeClick(event.recipeId)
            }
        }
    }

    HomeScreen(
        state = state,
        onAction = viewModel::onAction,
        onSearchClick = onSearchClick,
        onRecipeClick = onRecipeClick
    )
}
