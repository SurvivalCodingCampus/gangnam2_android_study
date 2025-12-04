package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class TabTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Tab_생성_후_첫번째_탭_클릭`() {
        val labels = listOf("label1", "label2")
        var result = ""

        composeTestRule.setContent {
            Tab(
                labels = labels,
                onValueChange = {
                    result = "$it 번째 탭을 클릭했습니다"
                }
            )
        }

        composeTestRule.onNodeWithText("label1").performClick()

        assertEquals("0 번째 탭을 클릭했습니다", result)
    }

    @Test
    fun `Tab_생성_후_두번째_탭_클릭`() {
        val labels = listOf("label1", "label2")
        var result = ""

        composeTestRule.setContent {
            Tab(
                labels = labels,
                onValueChange = {
                    result = "$it 번째 탭을 클릭했습니다"
                }
            )
        }

        composeTestRule.onNodeWithText("label2").performClick()

        assertEquals("1 번째 탭을 클릭했습니다", result)
    }
}