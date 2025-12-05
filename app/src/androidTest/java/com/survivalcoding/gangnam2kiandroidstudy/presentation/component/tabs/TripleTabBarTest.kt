package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.tabs

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TripleTabBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun TripleTabBar_firstTabClick() {
        var selectedIndex = 1

        composeTestRule.setContent {
            TripleTabBar(
                leftTab = "Left",
                centerTab = "Center",
                rightTab = "Right",
                selectedIndex = selectedIndex,
                onTabSelected = { selectedIndex = it }
            )
        }

        composeTestRule.waitForIdle()

        // 첫 번째 Label 클릭
        composeTestRule.onNodeWithText("Left").performClick()

        assertEquals(0, selectedIndex)
    }

    @Test
    fun TripleTabBar_secondTabClick() {
        var selectedIndex = 0

        composeTestRule.setContent {
            TripleTabBar(
                leftTab = "Left",
                centerTab = "Center",
                rightTab = "Right",
                selectedIndex = selectedIndex,
                onTabSelected = { selectedIndex = it }
            )
        }

        composeTestRule.waitForIdle()

        // 두 번째 Label 클릭
        composeTestRule.onNodeWithText("Center").performClick()

        assertEquals(1, selectedIndex)
    }

    @Test
    fun TripleTabBar_thirdTabClick() {
        var selectedIndex = 0

        composeTestRule.setContent {
            TripleTabBar(
                leftTab = "Left",
                centerTab = "Center",
                rightTab = "Right",
                selectedIndex = selectedIndex,
                onTabSelected = { selectedIndex = it }
            )
        }

        composeTestRule.waitForIdle()

        // 세 번째 Label 클릭
        composeTestRule.onNodeWithText("Right").performClick()
        assertEquals(2, selectedIndex)
    }
}