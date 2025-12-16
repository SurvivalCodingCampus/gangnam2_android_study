package com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import org.junit.Assert
import org.junit.Test

class SavedRecipesViewModelTest {
    @Test
    fun getUiState() {
        val viewModel = SavedRecipesViewModel(
            recipeRepository = RecipeRepositoryImpl(
                dataSource = RecipeDataSourceImpl(
                    context = ApplicationProvider.getApplicationContext<Context>()
                )
            ),
        )

        // then
        Assert.assertTrue(viewModel.uiState.value.recipes.isNotEmpty())

    }
}