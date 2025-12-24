package com.survivalcoding.gangnam2kiandroidstudy.presentation.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail.SavedRecipeItemRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipes.SearchRecipesRoot
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickingRecipeInSearchScreenNavigatesToDetails() {
        // Given
        composeTestRule.setContent {
            TestSearchNavigationRoot()
        }

        // When - Click on a recipe
        // "Traditional spare ribs baked" is from the hardcoded mock data in SavedRecipesDataSource
        composeTestRule.onNodeWithText("Traditional spare ribs baked").performClick()

        // Then - Verify we are on the details screen
        // SavedRecipeItemScreen shows the recipe name and "13k Reviewers"
        composeTestRule.onNodeWithText("13k Reviewers").assertIsDisplayed()
        // And the recipe name should still be visible (as title)
        composeTestRule.onNodeWithText("Traditional spare ribs baked").assertIsDisplayed()
    }
}

@Composable
fun TestSearchNavigationRoot() {
    // Start directly at Search
    val topLevelBackStack = rememberNavBackStack(Route.Search)
    
    NavDisplay(
        backStack = topLevelBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
             entry<Route.Search> {
                SearchRecipesRoot(onRecipeClick = {
                    topLevelBackStack.add(Route.RecipeItem(it))
                })
            }
            entry<Route.RecipeItem> { navEntry ->
                SavedRecipeItemRoot(navEntry.recipe)
            }
        }
    )
}