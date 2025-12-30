package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredients
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SavedRecipeDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `다이얼로그_링크복사_테스트`() {
        // Mock Data
        val mockRecipe = Recipe(
            category = "Indian",
            chef = "Chef John",
            id = 1,
            image = "",
            name = "Test Recipe",
            rating = 4.0,
            time = "20 min",
            isSaved = true,
            ingredients = listOf(
                Ingredients(
                    amount = 500,
                    ingredient = Ingredient(id = 1, name = "Pork", image = "")
                )
            )
        )

        var capturedLink = ""

        composeTestRule.setContent {
            // UI 상태를 테스트 내부에서 제어하기 위한 로컬 상태
            var state by remember { mutableStateOf(SavedRecipeDetailsState()) }

            SavedRecipeItemScreen(
                state = state,
                procedure = emptyList(),
                onValueChanged = {},
                onBackButtonClicked = {},
                onMoreButtonClicked = { show ->
                    state = state.copy(isDropDownMenuShow = show)
                },
                onShareDialogRequest = { show ->
                    // Share 클릭 시 메뉴는 닫히고 다이얼로그가 뜸 (Screen 로직 확인 필요)
                    // Screen 내부 DropdownMenuItem onClick에서 onMoreButtonClicked(false)를 호출하는지,
                    // 아니면 외부에서 처리해야 하는지에 따라 다르지만, 
                    // 보통 ViewModel이 둘 다 처리하므로 여기서도 둘 다 처리해줌.
                    state = state.copy(isShareDialogShow = show)
                },
                onCopyLink = { link ->
                    capturedLink = link
                }
            )
        }

        // 1. More 버튼 클릭 확인
        // 초기에는 메뉴가 없어야 함
        composeTestRule.onNodeWithText("Share").assertDoesNotExist()

        composeTestRule.onNodeWithContentDescription("more버튼").performClick()

        // 메뉴가 떴는지 확인
        composeTestRule.onNodeWithText("Share").assertIsDisplayed()

        // 2. Share 클릭 확인
        composeTestRule.onNodeWithText("Share").performClick()

        // 다이얼로그가 떴는지 확인 (Recipe Link 타이틀)
        composeTestRule.onNodeWithText("Recipe Link").assertIsDisplayed()

        // 3. Copy Link 버튼 클릭 및 클립보드 복사(콜백) 확인
        composeTestRule.onNodeWithText("Copy Link").performClick()

        assertEquals("app.Recipe.co/recipe?id=${mockRecipe.id}", capturedLink)
    }
}
