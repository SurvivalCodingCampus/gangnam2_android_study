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
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onAction = { action ->
            if (action == HomeAction.SearchRecipe) {
                onSearchClick()
            } else {
                viewModel.onAction(action)
            }
        }
    )

}