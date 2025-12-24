package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.card.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.AppTextStyles
import kotlinx.coroutines.flow.distinctUntilChanged
import androidx.compose.runtime.snapshotFlow

@Composable
fun SavedRecipesScreen(
    state: SavedRecipeState,
    onRefresh: () -> Unit,
    onRemoveBookmark: (Int) -> Unit,
) {
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            Text(
                text = "Saved Recipes",
                style = AppTextStyles.mediumTextBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                items(
                    items = state.recipes,
                    key = { it.id }
                ) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        modifier = Modifier.padding(vertical = 10.dp),
                        onBookmarkClick = {
                            onRemoveBookmark(recipe.id)
                        }
                    )
                }
            }
        }
    }
    LaunchedEffect(listState) {
        // 컴포즈 상태를 flow로
        snapshotFlow {
            val layoutInfo = listState.layoutInfo
            val lastVisibleIndex =
                layoutInfo.visibleItemsInfo.lastOrNull()?.index

            lastVisibleIndex == layoutInfo.totalItemsCount - 1
        }
            .distinctUntilChanged()
            .collect { isAtBottom ->
                if (isAtBottom && state.recipes.isNotEmpty()) {
                    snackbarHostState.showSnackbar(
                        message = "마지막 레시피까지 확인했습니다"
                    )
                }
            }
    }
}
