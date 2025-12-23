package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchRecipesRoot(
    onNavigateToBack: () -> Unit,
    onNavigateToRecipeDetail: (Long) -> Unit,
    viewModel: SearchRecipesViewModel = koinViewModel(),
) {
    // UI 상태
    val state by viewModel.state.collectAsStateWithLifecycle()

    // 스낵바 상태와 코루틴 스코프 준비
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                SearchRecipesEvent.SnackBarApplyFilter -> {
                    // Root에서 직접 스낵바 실행
                    snackbarHostState.showSnackbar(
                        message = "필터가 적용되었습니다.",
                        duration = SnackbarDuration.Short
                    )
                }

                SearchRecipesEvent.SnackBarCancelFilter -> {
                    snackbarHostState.showSnackbar(
                        message = "필터가 취소되었습니다.",
                        duration = SnackbarDuration.Short
                    )
                }

                SearchRecipesEvent.NavigateToBack -> onNavigateToBack()
                is SearchRecipesEvent.NavigateToRecipeDetail -> onNavigateToRecipeDetail(event.recipeId)
            }
        }
    }

    SearchRecipesScreen(
        state = state,
        onAction = { action -> viewModel.onAction(action) },
        onFilterAction = { action -> viewModel.onFilterAction(action) },
        snackbarHostState = snackbarHostState,
    )
}