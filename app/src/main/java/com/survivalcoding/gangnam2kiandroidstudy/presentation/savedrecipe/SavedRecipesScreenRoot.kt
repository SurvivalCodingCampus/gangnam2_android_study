package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipesScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: SavedRecipesViewModel = koinViewModel(),
    onCardClick: (Int) -> Unit = {}
) {
    val state = viewModel.state.collectAsState()
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }


    val isAtBottom by remember {
        derivedStateOf {
            !listState.canScrollForward && state.value.recipes.isNotEmpty()
        }
    }

    LaunchedEffect(isAtBottom) {
        if (isAtBottom) {
            snackbarHostState.showSnackbar("레시피의 마지막입니다.")
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppColors.white,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 100.dp)
            )
        }
    ) { innerPadding ->
        SavedRecipesScreen(
            modifier = modifier,
            state = state.value,
            listState = listState,
            onBookmarkClick = {
                viewModel.getRecipes(it)
            },
            onCardClick = onCardClick
        )
    }
}