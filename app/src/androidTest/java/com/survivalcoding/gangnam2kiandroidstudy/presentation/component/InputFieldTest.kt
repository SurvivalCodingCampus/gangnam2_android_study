package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class InputFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `inputField에_글자가_없으면_placeholder가_보여야한다`() {
        composeTestRule.setContent {
            InputField("test", "placeholder", "", onValueChange = {})

        }
        composeTestRule.onNodeWithText("placeholder").assertIsDisplayed()
    }

    @Test
    fun `inputField에_글자가_있으면_placeholder가_보이면_안된다`() {
        composeTestRule.setContent {
            InputField("test", "placeholder", "value", onValueChange = {})

        }
        composeTestRule.onNodeWithText("placeholder").assertIsNotDisplayed()
        composeTestRule.onNodeWithText("value").assertIsDisplayed()
    }

}