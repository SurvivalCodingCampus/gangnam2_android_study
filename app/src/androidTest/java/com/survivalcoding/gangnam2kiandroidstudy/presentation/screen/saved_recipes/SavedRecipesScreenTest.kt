package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class SavedRecipesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun savedRecipesAreDisplayed() {
        composeTestRule.setContent {
            SavedRecipesRoot()
        }

        composeTestRule.onNodeWithText("Traditional spare ribs baked").assertIsDisplayed()
        composeTestRule.onNodeWithText("Spice roasted chicken with flavored rice").assertIsDisplayed()
    }

    @Test
    fun clickingBookmarkRemovesRecipe() {
        composeTestRule.setContent {
            SavedRecipesRoot()
        }

        // Verify item exists
        composeTestRule.onNodeWithText("Traditional spare ribs baked").assertIsDisplayed()

        // Click the first bookmark icon
        composeTestRule.onAllNodesWithContentDescription("Bookmark")
            .onFirst()
            .performClick()

        // Verify item is removed
        composeTestRule.onNodeWithText("Traditional spare ribs baked").assertIsNotDisplayed()
    }
}