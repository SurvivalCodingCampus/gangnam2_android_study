package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import org.junit.Rule
import org.junit.Test

class RecipeDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRecipeDetailsScreen() {
        val recipe = Recipe(
            id = 1L,
            name = "spice roasted chicken with flavored rice",
            imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            chef = "Chef John",
            time = 20,
            rating = 4.0,
        )
        val profile = Profile(
            id = 1,
            name = "Afuwape Abiodun",
            imageUrl = "https://picsum.photos/id/259/200/300",
            address = "Lagos, Nigeria",
        )
        val ingredients = listOf(
            Ingredient(
                name = "Tomato",
                imageUrl = "",
                amount = "500g",
            ),
            Ingredient(
                name = "Cabbage",
                imageUrl = "",
                amount = "400g",
            ),
            Ingredient(
                name = "Taco",
                imageUrl = "",
                amount = "300g",
            ),
            Ingredient(
                name = "Slice Bread",
                imageUrl = "",
                amount = "200g",
            ),
        )
        val procedures = listOf(
            Procedure(
                recipeId = 1,
                step = 1,
                content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
            ),
            Procedure(
                recipeId = 1,
                step = 2,
                content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?\n" +
                        "Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
            ),
            Procedure(
                recipeId = 1,
                step = 3,
                content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
            ),
        )

        composeTestRule.setContent {
            RecipeDetailsScreen(
                uiState = RecipeDetailsUiState(
                    selectedTabIndex = 0,
                    recipe = recipe,
                    reviewCount = 13_000,
                    profile = profile,
                    ingredients = ingredients,
                    procedures = procedures,
                ),
            )
        }

        composeTestRule.onNode(hasText("spice roasted", substring = true)).assertIsDisplayed()
        composeTestRule.onNode(hasText("13K Reviews", substring = true)).assertIsDisplayed()

        composeTestRule.onNodeWithText("Afuwape Abiodun").assertIsDisplayed()
        composeTestRule.onNodeWithText("Lagos, Nigeria").assertIsDisplayed()

        composeTestRule.onNodeWithText("Follow").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ingredient").assertIsDisplayed()
        composeTestRule.onNodeWithText("Procedure").assertIsDisplayed()

        composeTestRule.onNodeWithText("1 serve").assertIsDisplayed()
        composeTestRule.onNodeWithText("4 items").assertIsDisplayed()

        composeTestRule.onNodeWithText("Tomato").assertIsDisplayed()
        composeTestRule.onNodeWithText("500g").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cabbage").assertIsDisplayed()
        composeTestRule.onNodeWithText("400g").assertIsDisplayed()
        composeTestRule.onNodeWithText("Taco").assertIsDisplayed()
        composeTestRule.onNodeWithText("300g").assertIsDisplayed()
    }

}