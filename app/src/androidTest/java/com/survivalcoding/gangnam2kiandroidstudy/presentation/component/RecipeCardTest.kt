package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import org.junit.Rule
import org.junit.Test

class RecipeCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRecipeCard() {
        val recipe = Recipe(
            name = "spice roasted chicken with flavored rice",
            imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            chef = "Chef John",
            time = 20,
            rating = 4.0,
        )

        composeTestRule.setContent {
            RecipeCard(
                recipe = recipe,
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

        composeTestRule.onNodeWithText("spice roasted chicken with flavored rice")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("By Chef John").assertIsDisplayed()
        composeTestRule.onNodeWithText("20 min").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("image").assertIsDisplayed()
    }
}