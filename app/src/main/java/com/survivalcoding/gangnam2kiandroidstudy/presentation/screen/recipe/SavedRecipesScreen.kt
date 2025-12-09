package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Items.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.appbar.CustomAppTopBar

@Composable
fun SavedRecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: SavedRecipesViewModel = viewModel(
        factory = SavedRecipesViewModel.Factory
    )
) {
    val recipesState = viewModel.recipes.collectAsState()

    CustomAppTopBar(
        text = stringResource(R.string.saved_recipes_title)
    )
    LazyColumn{
        items(recipesState.value) { recipe ->
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

@Preview(showBackground = true)
@Composable
private fun SavedRecipesScreenPreview() {
    SavedRecipesScreen()
}