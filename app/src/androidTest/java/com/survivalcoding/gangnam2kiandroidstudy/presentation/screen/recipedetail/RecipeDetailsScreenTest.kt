package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail

import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ShareDialog
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class `RecipeDetailsScreenTest` {

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

    @Test
    fun testCopyLink() {
        val recipe = Recipe(
            id = 1L,
            name = "spice roasted chicken with flavored rice",
            imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            chef = "Chef John",
            time = 20,
            rating = 4.0,
            shareUrl = "app.Recipe.co/jollof_rice",
        )

        composeTestRule.setContent {
            var uiState by remember {
                mutableStateOf(
                    RecipeDetailsUiState(
                        recipe = recipe,
                    ),
                )
            }

            RecipeDetailsScreen(
                uiState = uiState,
                onAction = { action ->
                    when (action) {
                        RecipeDetailsAction.OnMenuClick -> {
                            uiState = uiState.copy(isMenuVisible = true)
                        }

                        RecipeDetailsAction.OnShareClick -> {
                            uiState = uiState.copy(
                                isMenuVisible = false,
                                isShareDialogVisible = true,
                            )
                        }

                        is RecipeDetailsAction.OnCopyClick -> {
                            val context = InstrumentationRegistry.getInstrumentation().targetContext
                            val clipboard =
                                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = android.content.ClipData.newPlainText("share", action.text)
                            clipboard.setPrimaryClip(clip)
                        }

                        RecipeDetailsAction.OnShareDismissRequest -> {
                            uiState = uiState.copy(isShareDialogVisible = false)
                        }

                        else -> {}
                    }
                },
            )

            ShareDialog(
                isVisible = uiState.isShareDialogVisible,
                shareUrl = uiState.recipe?.shareUrl ?: "",
                onDismissRequest = {
                    uiState = uiState.copy(isShareDialogVisible = false)
                },
                onCopy = {
                    val context = InstrumentationRegistry.getInstrumentation().targetContext
                    val clipboard =
                        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = android.content.ClipData.newPlainText("share", it)
                    clipboard.setPrimaryClip(clip)
                },
            )
        }

        // 1. 메뉴를 누르고
        composeTestRule.onNodeWithContentDescription("more icon").performClick()

        // 2. share 버튼을 누르고
        composeTestRule.onNodeWithText("share").performClick()

        // 3. Copy Link를 누르고
        composeTestRule.onNodeWithText("Copy Link").performClick()

        // 4. 클립보드에 복사되었는지 확인
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val actualText = clipboard.primaryClip?.getItemAt(0)?.text?.toString()

        assertEquals(recipe.shareUrl, actualText)
    }

}