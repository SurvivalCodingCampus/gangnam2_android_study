package com.misterjerry.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class SmallButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `smallButton이_클릭된다`() {
        var isClicked = false
        composeTestRule.setContent {
            SmallButton("Button") {
                isClicked = true
            }
        }
        composeTestRule.onNodeWithText("Button")
            .performClick()
        assertTrue(isClicked)
    }


}