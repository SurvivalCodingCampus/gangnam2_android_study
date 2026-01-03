package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.search_recipes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SearchRecipesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `검색어_입력_시_레시피_필터링_테스트`() {
        // Given
        composeTestRule.setContent {
            SearchRecipesRoot()
        }

        // Wait for items to load (they are loaded in init from hardcoded source)
        composeTestRule.onNodeWithText("Traditional spare ribs baked").assertIsDisplayed()
        composeTestRule.onNodeWithText("Spice roasted chicken with flavored rice").assertIsDisplayed()

        // When - Type "ribs" in search field
        composeTestRule.onNodeWithTag("SearchField").performTextInput("ribs")

        // Then
        composeTestRule.onNodeWithText("Traditional spare ribs baked").assertIsDisplayed()
        composeTestRule.onNodeWithText("Spice roasted chicken with flavored rice").assertIsNotDisplayed()
    }
}
