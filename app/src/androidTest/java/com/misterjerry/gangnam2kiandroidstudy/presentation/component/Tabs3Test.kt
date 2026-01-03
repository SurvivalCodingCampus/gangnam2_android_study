package com.misterjerry.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class Tabs3Test {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun `라벨이_잘_뜨는지_테스트`() {
        composeTestRule.setContent {
            Tab3(listOf("왼쪽", "가운데", "오른쪽"), 0) {
            }
        }
        composeTestRule.onNodeWithText("오른쪽").assertIsDisplayed()
        composeTestRule.onNodeWithText("왼쪽").assertIsDisplayed()
        composeTestRule.onNodeWithText("가운데").assertIsDisplayed()
    }
}