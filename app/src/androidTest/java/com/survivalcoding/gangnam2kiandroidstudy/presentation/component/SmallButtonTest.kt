package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SmallButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `SmallButton_생성_및_테스트`() {
        val expected = "Clicked Small button!"
        var result = ""

        composeTestRule.setContent {
            SmallButton(
                text = "Small button",
                onClick = {
                    result = expected
                }
            )
        }

        composeTestRule.onNodeWithText("Small button").performClick()

        assertEquals(expected, result)
    }
}