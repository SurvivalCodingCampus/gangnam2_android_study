package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.textfields

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
        var text by mutableStateOf("")

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
        composeTestRule.onNodeWithText("Hello").assertExists()
    }

    @Test
    fun inputField_Disabled() {
        var text by mutableStateOf("")

        composeTestRule.setContent {
            InputField(
                value = text,
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
