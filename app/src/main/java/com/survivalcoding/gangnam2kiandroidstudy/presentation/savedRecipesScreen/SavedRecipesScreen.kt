package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedRecipesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.di.DependencyContainer
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SavedRecipesScreen(
    viewModel: SavedRecipesViewModel = viewModel(
        factory = DependencyContainer.provideSavedRecipesViewModelFactory(LocalContext.current)
    )
) {
    val recipes by viewModel.recipes.collectAsState()

    Column(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
        Box(
            modifier = Modifier.padding(top = 44.dp).height(47.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Saved recipes",
                fontWeight = FontWeight.SemiBold,
                style = AppTextStyles.mediumTextRegular
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 30.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(recipes) { recipe ->
                RecipeCard(recipe)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedRecipesScreenPreview() {
    val fakeRecipeRepository = object : RecipeRepository {
        override suspend fun getAllRecipes(): Result<List<Recipe>> {
            return Result.success(emptyList())
        }
    }
    val viewModel = SavedRecipesViewModel(fakeRecipeRepository)

    SavedRecipesScreen(viewModel)
}