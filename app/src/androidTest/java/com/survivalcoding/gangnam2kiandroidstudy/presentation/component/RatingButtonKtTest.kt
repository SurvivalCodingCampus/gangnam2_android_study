package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RatingButtonKtTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `레이팅버튼_선택하지_않은상태`() {
        composeRule.setContent {
            RatingButton(
                rating = 5,
                isSelected = false
            )
        }

        composeRule.onNodeWithText("5")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun `레이팅버튼_선택한_상태`() {
        composeRule.setContent {
            RatingButton(
                rating = 5,
                isSelected = true
            )
        }

        composeRule.onNodeWithText("5")
            .assertExists()
            .assertIsDisplayed()
    }
}