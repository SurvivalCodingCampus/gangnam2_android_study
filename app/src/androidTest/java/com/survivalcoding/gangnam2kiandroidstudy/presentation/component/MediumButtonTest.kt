package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MediumButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `MediumButton_생성_및_테스트`() {
        val expected = "Clicked Medium button!"
        var result = ""

        composeTestRule.setContent {
            MediumButton(
                text = "Medium button",
                onClick = {
                    result = expected
                }
            )
        }

        composeTestRule.onNodeWithText("Medium button").performClick()

        assertEquals(expected, result)
    }
}