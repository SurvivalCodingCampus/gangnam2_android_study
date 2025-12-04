package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class FilterButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testFilterButton() {
        var isSelected = false

        composeTestRule.setContent {
            FilterButton(
                text = "Text",
                isSelected = isSelected,
                onClick = { isSelected = !isSelected },
            )
        }

        composeTestRule.onNodeWithText("Text").assertIsDisplayed()
        composeTestRule.onNodeWithText("Text").performClick()
        assertTrue(isSelected)
    }
}