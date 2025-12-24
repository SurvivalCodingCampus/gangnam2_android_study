package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class MediumButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `mediumButton이_클릭된다`() {
        var isClicked = false
        composeTestRule.setContent {
            MediumButton(text = "Button", onClick = {
                isClicked = true
            })
        }
        composeTestRule.onNodeWithText("Button")
            .performClick()

        assertTrue(isClicked)

    }

}