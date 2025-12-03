package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.collections.immutable.toImmutableList
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class TabsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Tabs에_설정된_라벨들이_표시된다`() {
        val labels = listOf("로그인", "회원가입").toImmutableList()

        composeTestRule.setContent {
            Tabs(
                labels = labels,
                selectedIndex = 0,
                onTabSelected = {}
            )
        }

        composeTestRule.onNodeWithText("로그인").assertIsDisplayed()
        composeTestRule.onNodeWithText("회원가입").assertIsDisplayed()
    }

    @Test
    fun `Tabs에서_탭을_클릭하면_onTabSelected가_호출된다`() {
        val labels = listOf("첫 번째", "두 번째", "세 번째").toImmutableList()
        val clickedIndex = AtomicInteger(-1)

        composeTestRule.setContent {
            var selectedIndex by remember { mutableStateOf(0) }
            Tabs(
                labels = labels,
                selectedIndex = selectedIndex,
                onTabSelected = {
                    selectedIndex = it
                    clickedIndex.set(it)
                }
            )
        }

        composeTestRule.onNodeWithText("세 번째").performClick()

        assertEquals(2, clickedIndex.get())
    }

    @Test
    fun `Tabs는_최대_3개의_라벨만_표시한다`() {
        val labels = listOf("1", "2", "3", "4").toImmutableList()

        composeTestRule.setContent {
            Tabs(
                labels = labels,
                selectedIndex = 0,
                onTabSelected = {}
            )
        }

        composeTestRule.onNodeWithText("1").assertIsDisplayed()
        composeTestRule.onNodeWithText("2").assertIsDisplayed()
        composeTestRule.onNodeWithText("3").assertIsDisplayed()
        composeTestRule.onNodeWithText("4").assertDoesNotExist()
    }
}
