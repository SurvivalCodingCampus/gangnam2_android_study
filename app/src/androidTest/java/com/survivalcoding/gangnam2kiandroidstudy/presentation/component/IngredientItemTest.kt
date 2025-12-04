package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import org.junit.Rule
import org.junit.Test

class IngredientItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testIngredientItem() {
        val ingredient = Ingredient(
            name = "Tomatos",
            imageUrl = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
            amount = "500g",
        )

        composeTestRule.setContent {
            IngredientItem(
                ingredient = ingredient,
                imageLoader = {
                    Image(
                        painter = ColorPainter(AppColors.Primary100),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = it,
                    )
                },
            )
        }

        composeTestRule.onNodeWithText("Tomatos").assertIsDisplayed()
        composeTestRule.onNodeWithText("500g").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("image").assertIsDisplayed()
    }
}