package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bigButtonTest() {
        var counter = 0
        val expected = 1

        composeTestRule.setContent {
            BigButton(
                "Button",
                onClick = {
                    counter++
                }
            )
        }

        composeTestRule.onNodeWithText("Button").performClick()

        assertEquals(expected, counter)
    }

    @Test
    fun mediumButtonTest() {
        var counter = 0
        val expected = 1

        composeTestRule.setContent {
            MediumButton(
                "Button",
                onClick = {
                    counter++
                }
            )
        }

        composeTestRule.onNodeWithText("Button").performClick()

        assertEquals(expected, counter)
    }

    @Test
    fun smallButtonTest() {
        var counter = 0
        val expected = 1

        composeTestRule.setContent {
            SmallButton(
                "Button",
                onClick = {
                    counter++
                }
            )
        }

        composeTestRule.onNodeWithText("Button").performClick()

        assertEquals(expected, counter)
    }

}