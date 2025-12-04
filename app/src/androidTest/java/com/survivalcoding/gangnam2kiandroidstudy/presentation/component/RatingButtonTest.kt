package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class RatingButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRatingButton() {
        var isSelected = false

        composeTestRule.setContent {
            RatingButton(
                text = "5",
                isSelected = isSelected,
                onClick = { isSelected = !isSelected },
            )
        }

        composeTestRule.onNodeWithText("5").assertIsDisplayed()
        composeTestRule.onNodeWithText("5").performClick()
        assertTrue(isSelected)
    }
}