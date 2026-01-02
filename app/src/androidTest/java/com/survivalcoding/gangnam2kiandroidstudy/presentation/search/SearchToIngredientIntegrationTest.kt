package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.core.di.useCaseModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.viewModelModule
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeIngridentRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngridentRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.recipeDetail.RecipeDetailScreenRoot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class SearchToIngredientIntegrationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        stopKoin()
        startKoin {
            modules(
                viewModelModule,
                useCaseModule,
                module {
                    single<RecipeRepository> { FakeRecipeRepository() }
                    single<ProcedureRepository> { FakeProcedureRepository() }
                    single<IngridentRepository> { FakeIngridentRepository() }
                }
            )
        }
    }

    @Test
    fun search_to_ingredient_navigation_and_state_test() {
        // Given
        composeTestRule.setContent {
            TestNavigationRoot()
        }

        // 1. Search for a recipe
        composeTestRule.onNodeWithText("Search Recipe")
            .performTextInput("Traditional")

        // Wait for results (Debounce + Network Delay)
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("Traditional spare ribs baked")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // 2. Click on the recipe item
        composeTestRule.onNodeWithText("Traditional spare ribs baked")
            .performClick()

        // 3. Verify Navigation to Ingredient Screen and Data Display
        // Wait for the detail screen to load (Fake repository might be instant, but let's be safe)
        // We expect to see "Tomato" (Ingredient) which comes from FakeIngridentRepository. Default tab is 0 (Ingredients).
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.onAllNodesWithText("Tomato")
                .fetchSemanticsNodes().isNotEmpty()
        }
        
                composeTestRule.onNodeWithText("Tomato").assertIsDisplayed()
        
                // Also verify the title is still visible (it should be on the detail screen too, usually). 
        
                // Note: It might appear multiple times (in card and header), so we check existence.
        
                assert(
        
                    composeTestRule.onAllNodesWithText("Traditional spare ribs baked")
        
                        .fetchSemanticsNodes().isNotEmpty()
        
                )
        
        
        
                // 4. Verify Back Navigation and State Preservation
        // We simulate back press by clicking the back button we just enabled
        composeTestRule.onNodeWithContentDescription("back").performClick() // contentDescription is "back"

        // Verify we are back at Search Screen by checking the query text is present
        composeTestRule.onNodeWithText("Traditional").assertIsDisplayed()

        // Verify state is preserved (Results are still there)
        composeTestRule.onNodeWithText("Traditional spare ribs baked").assertIsDisplayed()
    }

    @Composable
    fun TestNavigationRoot() {
        // Start directly at SearchRecipe for this test
        val backStack = rememberNavBackStack(Route.SearchRecipe)

        NavDisplay(
            backStack = backStack,
            entryProvider = entryProvider {
                entry<Route.SearchRecipe> {
                    SearchRecipeScreenRoot(
                        onRecipeClick = { recipeId ->
                            backStack.add(Route.RecipeDetail(recipeId = recipeId))
                        }
                    )
                }
                entry<Route.RecipeDetail> { route ->
                    RecipeDetailScreenRoot(
                        recipeId = route.recipeId,
                        onBackClick = {
                            backStack.remove(backStack.last())
                        }
                    )
                }
            }
        )
    }
}
