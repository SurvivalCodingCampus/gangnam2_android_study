package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeProcedureUseCase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient.IngredientRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient.IngredientViewModel
import org.junit.Rule
import org.junit.Test

class SearchToIngredientIntegrationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navigate_from_list_to_detail_and_back() {
        // 1. Fake Data & Dependencies
        val targetRecipe = Recipe(
            id = 99,
            category = RecipeCategory.ITALIAN,
            name = "Test Spice Roasted Chicken",
            imageUrl = "",
            chef = "Test Chef",
            time = "20 min",
            rating = 4.0,
            ingredients = emptyList()
        )

        val fakeRepository = object : RecipeRepository {
            override suspend fun findRecipe(id: Long): Result<Recipe, String> {
                return if (id == targetRecipe.id) Result.Success(targetRecipe)
                else Result.Error("Not found")
            }

            override suspend fun findRecipes(): Result<List<Recipe>, String> {
                return Result.Success(listOf(targetRecipe))
            }
        }

        // Fake UseCase
        val fakeProcedureRepository = object : ProcedureRepository {
            override suspend fun getProcedure(id: Long): List<String> = emptyList()
        }
        val fakeUseCase = GetRecipeProcedureUseCase(fakeProcedureRepository)

        // ViewModel
        val searchViewModel = SearchRecipesViewModel(fakeRepository)
        val ingredientViewModel = IngredientViewModel(fakeRepository, fakeUseCase)

        // 2. 네비게이션을 포함한 컨텐츠 설정
        composeTestRule.setContent {
            val backStack = rememberNavBackStack(Route.SearchRecipes)

            NavDisplay(
                backStack = backStack,
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator(),
                ),
                entryProvider = entryProvider {
                    entry<Route.SearchRecipes> {
                        SearchRecipesRoot(
                            viewModel = searchViewModel,
                            onNavigateToRecipeDetail = { id ->
                                backStack.add(Route.RecipeDetail(id))
                            },
                            onNavigateToBack = {
                                // 테스트 루트에서는 앱 종료 처리가 필요 없음
                            }
                        )
                    }
                    entry<Route.RecipeDetail> { entry ->
                        IngredientRoot(
                            viewModel = ingredientViewModel,
                            recipeId = entry.recipeId,
                            onBackClick = {
                                backStack.removeAt(backStack.lastIndex)
                            }
                        )
                    }
                }
            )
        }

        // 3. 목록 화면 확인 및 클릭
        composeTestRule.waitForIdle()
        // 목록이 로드될 때까지 대기
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("Test Spice Roasted Chicken").fetchSemanticsNodes().isNotEmpty()
        }

        // 목록 아이템이 표시되는지 확인
        composeTestRule.onNodeWithText("Test Spice Roasted Chicken").assertIsDisplayed()

        // 아이템 클릭
        composeTestRule.onNodeWithText("Test Spice Roasted Chicken").performClick()

        // 4. 상세 화면 확인 (네비게이션 이동 및 데이터 전달 확인)
        // 상세 화면 제목이 나타날 때까지 대기 (IngredientScreen에 이름이 표시됨)
        composeTestRule.waitUntil(timeoutMillis = 3000) {
             // 쉐프 이름이나 상세 화면 전용 요소가 나타나는지 확인
             composeTestRule.onAllNodesWithText("Test Chef").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("Test Spice Roasted Chicken").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test Chef").assertIsDisplayed()

        // 5. 뒤로가기 탐색 확인
        // 뒤로가기 버튼 클릭 (콘텐츠 설명: "뒤로가기 버튼")
        composeTestRule.onNodeWithContentDescription("뒤로가기 버튼").performClick()

        // 목록 화면으로 다시 돌아올 때까지 대기
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.onAllNodesWithText("Test Spice Roasted Chicken").fetchSemanticsNodes().isNotEmpty()
        }

        // 목록으로 돌아왔는지 확인 (엄밀히 확인하려면 상세 정보가 사라졌는지도 체크 가능)
        composeTestRule.onNodeWithText("Test Spice Roasted Chicken").assertIsDisplayed()
    }
}
