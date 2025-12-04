package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.buttons

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BigButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bigButton_enabled_callsOnClick() {
        var clickedCount = 0

        composeTestRule.setContent {
            BigButton(
                text = "Button",
                enabled = true,
                onClick = { clickedCount++ }
            )
        }

        composeTestRule.onNodeWithText("Button").performClick()

        assertEquals(1, clickedCount)
    }

    @Test
    fun bigButton_disabled_doesNotCallOnClick() {
        var clickedCount = 0

        composeTestRule.setContent {
            BigButton(
                text = "Disabled",
                enabled = false,
                onClick = { clickedCount++ }
            )
        }

        composeTestRule.onNodeWithText("Disabled").performClick()

        assertEquals(0, clickedCount)
    }
}
