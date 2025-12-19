package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = koinViewModel(),
    onSearchClick: () -> Unit = {},
    onRecipeClick: (Int) -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is HomeAction.SelectCategory -> {
                    viewModel.onAction(action)
                }
                is HomeAction.SearchRecipe -> onSearchClick()
                is HomeAction.SelectRecipe -> onRecipeClick(action.id)
                is HomeAction.BookmarkRecipe -> {
                    viewModel.onAction(action)
                }
            }
        }
    )

}