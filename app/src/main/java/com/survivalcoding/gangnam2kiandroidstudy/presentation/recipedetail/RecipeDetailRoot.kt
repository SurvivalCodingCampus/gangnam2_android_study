package com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.di.DependencyContainer

@Composable
fun RecipeDetailRoot(viewModel: RecipeDetailViewModel = viewModel(factory = DependencyContainer.provideRecipeDetailViewModelFactory(LocalContext.current))) {
    RecipeDetailScreen(
        recipe = Recipe(
            id = 0,
            category = "category",
            name = "name",
            image = "image",
            chef = "chef",
            time = "time",
            rating = 0.0
        ),
        ingredients = listOf(),
        procedure = "",
        onFollowClick = {}
    )
}