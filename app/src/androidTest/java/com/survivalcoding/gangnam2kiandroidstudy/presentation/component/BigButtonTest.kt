package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.jetbrains.annotations.TestOnly
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class BigButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `BigButton_생성_및_테스트`() {
        val expected = "Clicked Big button!"
        var result = ""

        composeTestRule.setContent {
            BigButton(
                text = "Big button",
                onClick = {
                    result = expected
                }
            )
        }

        composeTestRule.onNodeWithText("Big button").performClick()

        assertEquals(expected, result)
    }
}