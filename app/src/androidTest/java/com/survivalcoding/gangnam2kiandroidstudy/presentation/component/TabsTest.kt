package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class TabsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tabs2Test() {
        val expected = 1
        var actual = 0
        composeTestRule.setContent {
            Tabs2(
                labels = listOf("Label1", "Label2"),
                selectedIndex = 1,
                onValueChange = {
                    actual = 1
                }
            )
        }

        composeTestRule.onNodeWithText("Label2").performClick()

        assertEquals(expected,actual)
    }

    @Test
    fun tabs3Test() {
        val expected = 0
        var actual = 0
        composeTestRule.setContent {
            Tabs3(
                labels = listOf("Label1", "Label2","Label3"),
                selectedIndex = 0,
                onValueChange = {
                    actual = 0
                }
            )
        }

        composeTestRule.onNodeWithText("Label1").performClick()

        assertEquals(expected,actual)
    }

}