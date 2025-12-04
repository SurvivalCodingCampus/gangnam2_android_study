package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SmallButtonKtTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `스몰버튼_클릭이_정상적으로_호출된다`() {
        var clicked = false

        composeRule.setContent {
            SmallButton(text = "Button", onClick = { clicked = true })
        }

        composeRule
            .onNodeWithText("Button")
            .performClick()

        assertTrue(clicked)
    }
}