@file:OptIn(ExperimentalCoroutinesApi::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockRecipeRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class SavedRecipesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSavedRecipesScreen() = runTest {
        val recipes = MockRecipeRepositoryImpl.mockRecipes

        composeTestRule.setContent {
            SavedRecipesScreen(
                uiState = SavedRecipesUiState(
                    recipes = recipes,
                ),
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Saved Recipes").assertIsDisplayed()

        recipes.let { data ->
            (0..3).forEach { index ->
                composeTestRule.onNodeWithText(data[index].name).assertIsDisplayed()
                composeTestRule.onNodeWithText("By ${data[index].chef}").assertIsDisplayed()
            }
        }
    }
}