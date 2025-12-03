package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.textfields

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class InputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun inputField_Enabled() {
        var text = ""

        composeTestRule.setContent {
            InputField(
                value = text,
                onValueChange = { text = it },
                placeholderText = "Placeholder"
            )
        }

        val inputNode = composeTestRule.onNodeWithText("Placeholder")
        inputNode.assertIsDisplayed()

        inputNode.performTextInput("Hello")
        assert(text == "Hello")
    }

    @Test
    fun inputField_Disabled() {
        composeTestRule.setContent {
            InputField(
                value = "",
                onValueChange = {},
                placeholderText = "Disabled Input",
                enabled = false
            )
        }

        composeTestRule.onNodeWithText("Disabled Input")
            .assertIsDisplayed()
            .assertIsNotEnabled()
    }
}
