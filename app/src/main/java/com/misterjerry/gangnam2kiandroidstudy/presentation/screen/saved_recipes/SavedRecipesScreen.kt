package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.misterjerry.gangnam2kiandroidstudy.R
import com.misterjerry.gangnam2kiandroidstudy.domain.model.Recipe
import com.misterjerry.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.misterjerry.gangnam2kiandroidstudy.presentation.ui.AppTextStyles
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun SavedRecipesScreen(
    state: SavedRecipesState,
    onBookMarkClick: (Int) -> Unit,
    onRecipeClick: (Recipe) -> Unit = {},
    onReachEnd: () -> Unit,
    lazyListState: LazyListState
) {

    LaunchedEffect(lazyListState) {
        snapshotFlow {
            val currentIndex = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            currentIndex == state.savedRecipesList.size - 1
        }
            .distinctUntilChanged()
            .collect { bool ->
                if (bool) {
                    onReachEnd()
                }
            }
    }
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(54.dp))
            Text(
                stringResource(R.string.saved_recipes),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = AppTextStyles.mediumTextBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (state.savedRecipesList.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                stringResource(R.string.no_saved_recipes),
                                style = AppTextStyles.normalTextRegular
                            )
                        }
                    }
                } else {
                    items(state.savedRecipesList) { recipe ->
                        RecipeCard(
                            recipe = recipe,
                            isSaved = recipe.isSaved,
                            onBookMarkClick = { onBookMarkClick(recipe.id) },
                            onRecipeClick = { onRecipeClick(recipe) }
                        )
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedRecipesScreenPreview() {
    val recipe = Recipe(
        category = "Indian",
        chef = "Chef John",
        id = 1,
        image = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
        name = "Traditional spare ribs baked",
        rating = 4.0,
        time = "20 min",
        ingredients = emptyList(),
        isSaved = true
    )
    SavedRecipesScreen(
        state = SavedRecipesState(
            savedRecipesList = listOf(recipe, recipe, recipe)
        ),
        onBookMarkClick = {},
        onReachEnd = {},
        lazyListState = rememberLazyListState()
    )
}