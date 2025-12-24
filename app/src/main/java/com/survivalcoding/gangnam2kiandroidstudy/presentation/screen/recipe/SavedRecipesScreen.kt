package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.appbar.CustomAppTopBar
import kotlinx.coroutines.flow.distinctUntilChanged


@Composable
fun SavedRecipesScreen(
    state: SavedRecipesState,
    modifier: Modifier = Modifier,
    onAction: (SavedRecipesAction) -> Unit = {},
) {
    // 리스트 끝에 도달
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            val layoutInfo = listState.layoutInfo
            val lastVisibleIndex =
                layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItems = layoutInfo.totalItemsCount

            lastVisibleIndex == totalItems - 1 && totalItems > 0
        }
            .distinctUntilChanged() // false → true 가 될 때만 통과
            .collect { isAtBottom ->
                if (isAtBottom) {
                    onAction(SavedRecipesAction.ReachedBottom) // action
                }
            }
    }

    Column(modifier = modifier.fillMaxSize()) {
        CustomAppTopBar(
            text = stringResource(R.string.saved_recipes_title)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("SavedRecipesList"),
            state = listState
        ) {
            items(state.recipes) { recipe ->
                val isBookmarked = recipe.id in state.bookmarkedIds

                RecipeCard(
                    modifier = Modifier.padding(vertical = 10.dp),
                    name = recipe.title,
                    imageUrl = recipe.imageUrls,
                    chef = recipe.chefName,
                    time = recipe.time,
                    rating = recipe.rating,
                    isBookmarked = isBookmarked,
                    onBookmarkClick = {
                        onAction(
                            SavedRecipesAction.BookmarkClicked(recipe.id)
                        )
                    },
                    onClick = {
                        onAction(
                            SavedRecipesAction.RecipeClicked(recipe.id)
                        )
                    }
                )
            }
        }
    }
}

