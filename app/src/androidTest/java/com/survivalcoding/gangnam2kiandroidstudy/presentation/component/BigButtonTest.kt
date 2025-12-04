package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

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
    fun `BigButton이_클릭된다`() {
        var isClicked = false
        composeTestRule.setContent {
            BigButton({ isClicked = true }, "button")
        }
        composeTestRule.onNodeWithText("button").performClick()

        assertEquals(true, isClicked)
    }

}