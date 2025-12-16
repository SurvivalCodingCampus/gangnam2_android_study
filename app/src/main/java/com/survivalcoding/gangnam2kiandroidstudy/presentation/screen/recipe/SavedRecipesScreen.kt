package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.appbar.CustomAppTopBar

@Composable
fun SavedRecipesScreen(
    state: SavedRecipesState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        CustomAppTopBar(
            text = stringResource(R.string.saved_recipes_title)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.recipes) { recipe ->
                RecipeCard(
                    modifier = Modifier.padding(vertical = 10.dp),
                    name = recipe.title,
                    imageUrl = recipe.imageUrls,
                    chef = recipe.chef,
                    time = recipe.time,
                    rating = recipe.rating,
                )
            }
        }
    }
}
