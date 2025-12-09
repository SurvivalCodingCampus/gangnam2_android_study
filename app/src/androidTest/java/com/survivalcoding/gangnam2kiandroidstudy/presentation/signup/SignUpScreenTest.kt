package com.survivalcoding.gangnam2kiandroidstudy.presentation.signup

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSignUpScreen() {

        composeTestRule.setContent {
            SignUpScreen()
        }

        composeTestRule.onNodeWithText("Create an account").assertIsDisplayed()
        composeTestRule.onNodeWithText("Let's help you set up your account,\nit won't take long.")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Name").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
        composeTestRule.onNodeWithText("Confirm Password").assertIsDisplayed()
        composeTestRule.onNodeWithText("Accept terms & Condition").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sign Up").assertIsDisplayed()
        composeTestRule.onNodeWithText("Or Sign in With").assertIsDisplayed()
        composeTestRule.onNodeWithText("Already a member?").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sign In").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("google").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("facebook").assertIsDisplayed()

        val testName = "test"
        val testEmail = "test@test.com"
        val testPassword = "1234"
        val testConfirmPassword = "12345"

        composeTestRule.onNodeWithText("Enter Name").performTextInput(testName)
        composeTestRule.onNodeWithText("Enter Email").performTextInput(testEmail)
        composeTestRule.onNodeWithText("Enter Password").performTextInput(testPassword)
        composeTestRule.onNodeWithText("Retype Password").performTextInput(testConfirmPassword)
        composeTestRule.onNodeWithText(testName).assertExists()
        composeTestRule.onNodeWithText(testEmail).assertExists()
        composeTestRule.onNodeWithText(testPassword).assertExists()
        composeTestRule.onNodeWithText(testConfirmPassword).assertExists()
    }
}