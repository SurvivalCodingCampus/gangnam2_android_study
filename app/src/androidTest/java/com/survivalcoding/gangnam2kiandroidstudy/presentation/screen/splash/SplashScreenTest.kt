package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSplashScreen() {
        composeTestRule.setContent {
            SplashScreen(
                uiState = SplashUiState(
                    isOnline = true,
                ),
            )
        }

        composeTestRule.onNodeWithContentDescription("splash icon").assertIsDisplayed()
        composeTestRule.onNodeWithText("100K+ Premium Recipe").assertIsDisplayed()
        composeTestRule.onNodeWithText("Get\nCooking").assertIsDisplayed()
        composeTestRule.onNodeWithText("Simple way to find Tasty Recipe").assertIsDisplayed()

        composeTestRule.onNodeWithText("Start Cooking").assertIsDisplayed()
        composeTestRule.onNodeWithText("Start Cooking").assertIsEnabled()
    }

    @Test
    fun testOfflineSplashScreen() {
        composeTestRule.setContent {
            SplashScreen(
                uiState = SplashUiState(
                    isOnline = false,
                ),
            )
        }

        composeTestRule.onNodeWithText("Start Cooking").assertIsDisplayed()
        composeTestRule.onNodeWithText("Start Cooking").assertIsNotEnabled()
    }
}