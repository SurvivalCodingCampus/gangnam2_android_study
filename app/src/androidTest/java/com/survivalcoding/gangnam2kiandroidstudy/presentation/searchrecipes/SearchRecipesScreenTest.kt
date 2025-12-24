package com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SearchRecipesScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val testRecipes = listOf(
        Recipe(
            id = 1,
            name = "Traditional Spare Ribs",
            image = "image1",
            chef = "Chef John",
            time = "20 min",
            rating = 4.5,
            category = "Korean"
        ),
        Recipe(
            id = 2,
            name = "Spice Roasted Chicken",
            image = "image2",
            chef = "Chef Mark",
            time = "30 min",
            rating = 4.8,
            category = "Italian"
        )
    )

    @Test
    fun displayRecipes() {
        // Given
        val state = SearchRecipesState(
            filteredRecipes = testRecipes
        )

        // When
        composeTestRule.setContent {
            SearchRecipesScreen(
                state = state,
                onAction = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Traditional Spare Ribs").assertIsDisplayed()
        composeTestRule.onNodeWithText("Spice Roasted Chicken").assertIsDisplayed()
        composeTestRule.onNodeWithText("By Chef John").assertIsDisplayed()
    }

    @Test
    fun triggerSearchAction() {
        // Given
        val state = SearchRecipesState()
        var lastAction: SearchRecipesAction? = null

        // When
        composeTestRule.setContent {
            SearchRecipesScreen(
                state = state,
                onAction = { lastAction = it }
            )
        }

        // SearchField placeholder
        composeTestRule.onNodeWithText("Search recipe")
            .performTextInput("Pasta")

        // Then
        assertEquals(SearchRecipesAction.OnSearchQueryChange("Pasta"), lastAction)
    }

    @Test
    fun triggerFilterSheet() {
        // Given
        val state = SearchRecipesState()

        // When
        composeTestRule.setContent {
            SearchRecipesScreen(
                state = state,
                onAction = {}
            )
        }

        // Click on filter icon
        composeTestRule.onNodeWithContentDescription("setting icon")
            .performClick()

        // Then - Verify BottomSheet content appears
        composeTestRule.onNodeWithText("Filter Search").assertIsDisplayed()
    }

    @Test
    fun triggerBackAction() {
        // Given
        val state = SearchRecipesState()
        var lastAction: SearchRecipesAction? = null

        // When
        composeTestRule.setContent {
            SearchRecipesScreen(
                state = state,
                onAction = { lastAction = it }
            )
        }

        // Click on back button
        composeTestRule.onNodeWithContentDescription("뒤로가기 버튼")
            .performClick()

        // Then
        assertEquals(SearchRecipesAction.OnBackClick, lastAction)
    }
}
