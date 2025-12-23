package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SavedRecipesScreen(
    modifier: Modifier = Modifier,
    state: SavedRecipesState = SavedRecipesState(),
    listState: LazyListState,
    onBookmarkClick: (Int) -> Unit = {},
    onCardClick: (Int) -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(54.dp))
        Text(
            text = "Saved Recipes",
            style = AppTextStyles.mediumTextBold
        )
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            contentPadding = PaddingValues(
                bottom = 120.dp
            ),
            state = listState
        ) {
            items(state.recipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    modifier = Modifier.padding(vertical = 10.dp),
                    onBookmarkClick = {
                        onBookmarkClick(it)
                    },
                    onCardClick = {
                        onCardClick(it)
                    }
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SavedRecipesScreenPreview() {
    SavedRecipesScreen(
        listState = rememberLazyListState()
    )
}