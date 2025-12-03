package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class InputFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `InputField_생성_후_콜백_확인하기`() {
        val label = "Input Field Test"

        val expected = "hello"
        var result = ""

        composeTestRule.setContent {
            InputField(
                label = label,
                onValueChange = { result = it }
            )
        }

        composeTestRule.onNodeWithText("Placeholder").performTextInput(expected)

        assertEquals(expected, result)
    }
}