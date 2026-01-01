package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipesUseCase
import org.junit.Rule
import org.junit.Test

class SearchRecipesIntegrationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun search_recipe_scenario() {
        // 1. Fake Repository 준비
        val fakeRepository = object : RecipeRepository {
            override suspend fun findRecipe(id: Long): Result<Recipe, String> {
                return Result.Error("Not implemented")
            }

            override suspend fun findRecipes(): Result<List<Recipe>, String> {
                return Result.Success(
                    listOf(
                        Recipe(
                            id = 1,
                            category = RecipeCategory.ASIAN,
                            name = "Traditional Spare Ribs Baked",
                            imageUrl = "",
                            chef = "Chef A",
                            time = "30 min",
                            rating = 4.5,
                            ingredients = emptyList()
                        ),
                        Recipe(
                            id = 2,
                            category = RecipeCategory.ITALIAN,
                            name = "Spice Roasted Chicken",
                            imageUrl = "",
                            chef = "Chef B",
                            time = "20 min",
                            rating = 4.0,
                            ingredients = emptyList()
                        )
                    )
                )
            }
        }

        // 2. ViewModel 및 Screen 설정
        val viewModel = SearchRecipesViewModel(GetRecipesUseCase(fakeRepository))

        composeTestRule.setContent {
            val state by viewModel.state.collectAsState()
            val snackbarHostState = remember { SnackbarHostState() }

            SearchRecipesScreen(
                state = state,
                snackbarHostState = snackbarHostState,
                onAction = viewModel::onAction,
                onFilterAction = viewModel::onFilterAction
            )
        }

        // 3. 초기 상태 확인 (모든 레시피 표시)
        composeTestRule.onNodeWithText("Traditional Spare Ribs Baked").assertIsDisplayed()
        composeTestRule.onNodeWithText("Spice Roasted Chicken").assertIsDisplayed()

        // 4. 검색어 입력 (Chicken)
        // Placeholder text를 사용하여 검색창 찾기
        composeTestRule.onNodeWithText("Search recipe").performTextInput("Chicken")

        // 5. 대기 (Debounce 500ms + 필터링)
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            // Spare Ribs가 사라질 때까지 대기
            composeTestRule.onAllNodesWithText("Traditional Spare Ribs Baked").fetchSemanticsNodes().isEmpty()
        }

        // 6. 결과 확인
        composeTestRule.onNodeWithText("Spice Roasted Chicken").assertIsDisplayed()
        composeTestRule.onNodeWithText("Traditional Spare Ribs Baked").assertDoesNotExist()
    }
}
