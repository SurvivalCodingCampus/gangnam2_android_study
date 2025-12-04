package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class RatingDialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun star_아이콘을_누르면_해당_점수를_콜백으로_리턴한다() {
        var testRating = 0

        composeTestRule.setContent {
            RatingDialog(
                title = "Rate recipe",
                onChange = { newRating ->
                    testRating = newRating
                }
            )
        }

        composeTestRule.onNodeWithContentDescription("star icon 1")
            .performClick()
        assertEquals(1, testRating)

        composeTestRule.onNodeWithContentDescription("star icon 2")
            .performClick()
        assertEquals(2, testRating)

        composeTestRule.onNodeWithContentDescription("star icon 3")
            .performClick()
        assertEquals(3, testRating)

        composeTestRule.onNodeWithContentDescription("star icon 4")
            .performClick()
        assertEquals(4, testRating)

        composeTestRule.onNodeWithContentDescription("star icon 5")
            .performClick()
        assertEquals(5, testRating)
    }

    @Test
    fun 점수를_선택하고_Send_버튼을_클릭하면_점수를_콜백으로_리턴한다() {
        var resultRating = 0

        composeTestRule.setContent {
            RatingDialog(
                title = "Rate recipe",
                onClick = {
                    resultRating = it
                }
            )
        }

        composeTestRule.onNodeWithContentDescription("star icon 1")
            .performClick()
        composeTestRule.onNodeWithText("Send")
            .performClick()
        assertEquals(1, resultRating)

        composeTestRule.onNodeWithContentDescription("star icon 2")
            .performClick()
        composeTestRule.onNodeWithText("Send")
            .performClick()
        assertEquals(2, resultRating)

        composeTestRule.onNodeWithContentDescription("star icon 3")
            .performClick()
        composeTestRule.onNodeWithText("Send")
            .performClick()
        assertEquals(3, resultRating)

        composeTestRule.onNodeWithContentDescription("star icon 4")
            .performClick()
        composeTestRule.onNodeWithText("Send")
            .performClick()
        assertEquals(4, resultRating)

        composeTestRule.onNodeWithContentDescription("star icon 5")
            .performClick()
        composeTestRule.onNodeWithText("Send")
            .performClick()
        assertEquals(5, resultRating)
    }

    @Test
    fun 점수를_선택하지_않고_Send_버튼을_클릭하면_동작을_하지_않는다() {
        var resultRating = 0

        composeTestRule.setContent {
            RatingDialog(
                title = "Rate recipe",
                onClick = {
                    resultRating = it
                }
            )
        }

        composeTestRule.onNodeWithText("Send")
            .performClick()
        assertEquals(0, resultRating)
    }
}