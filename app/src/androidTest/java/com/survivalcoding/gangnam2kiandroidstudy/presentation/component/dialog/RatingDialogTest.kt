package com.survivalcoding.gangnam2kiandroidstudy.presentation.component.dialog

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
    fun click_Star_And_Send() {
        var returnedValue: Int? = null

        composeTestRule.setContent {
            RateDialog(
                title = "Rate recipe",
                actionName = "Send",
                onChange = { returnedValue = it },
                onDismiss = {}
            )
        }

        // 별 3개 클릭
        composeTestRule.onNodeWithContentDescription("3 stars")
            .performClick()

        // Send 버튼 클릭
        composeTestRule.onNodeWithText("Send")
            .performClick()

        // onChange 콜백이 3을 반환했는지 확인
        assertEquals(3, returnedValue)
    }
}
