package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class RatingDialogTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRatingDialog() {
        val title = "Rate recipe"
        val actionName = "Send"
        var count = 0

        composeTestRule.setContent {
            RatingDialog(
                title = title,
                actionName = actionName,
                onChange = {
                    count = it
                }
            )
        }

        composeTestRule.onNodeWithText(title).assertExists()
        composeTestRule.onNodeWithText(actionName).assertExists()
        composeTestRule.onNodeWithContentDescription("star_2").performClick()
        composeTestRule.onNodeWithText(actionName).performClick()

        assertEquals(3, count)



    }

}