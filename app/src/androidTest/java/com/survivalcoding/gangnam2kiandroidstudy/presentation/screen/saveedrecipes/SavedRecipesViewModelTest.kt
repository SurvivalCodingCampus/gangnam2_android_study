package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saveedrecipes

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.test.core.app.ApplicationProvider
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes.SavedRecipesViewModel
import org.junit.Assert.assertTrue
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
            savedStateHandle = SavedStateHandle()
        )

        // then
        assertTrue(viewModel.uiState.value.recipes.isNotEmpty())

    }
}