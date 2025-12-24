package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.survivalcoding.gangnam2kiandroidstudy.core.util.MockCopyManager
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockIngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockProfileRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockRecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail.RecipeDetailsEvent
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail.RecipeDetailsScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail.RecipeDetailsViewModel
import org.junit.Rule
import org.junit.Test

class SearchRecipesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun search_scenario_debounce() {
        // Arrange
        val repository = MockRecipeRepositoryImpl
        val viewModel = SearchRecipesViewModel(repository)

        composeTestRule.setContent {
            val uiState by viewModel.uiState.collectAsState()
            SearchRecipesScreen(
                uiState = uiState,
                onAction = viewModel::onAction,
            )
        }

        // Act
        // 1. 검색창 찾기 및 키워드 입력
        // Placeholder 텍스트 노드가 아닌, 실제 입력 가능한 TextField 노드를 찾기 위해 hasSetTextAction()을 사용합니다.
        composeTestRule.onNode(hasSetTextAction())
            .performTextInput("Traditional")

        // 2. 잠시 대기 (ViewModel의 debounce 시간인 500ms 이상 대기)
        // 검색 로직이 실행되어 'isSearched' 상태가 true가 되고, 데이터 로딩이 끝날 때까지 대기합니다.
        // 초기 로딩 상태와 구분하기 위해 isSearched 체크가 필수적입니다.
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            val state = viewModel.uiState.value
            state.isSearched && !state.isLoading
        }

        // 3. 결과 확인
        // MockRecipeRepositoryImpl에서 반환된 첫 번째 아이템이 표시되는지 검증
        composeTestRule.onNodeWithText("Traditional spare ribs baked")
            .assertIsDisplayed()

        // 검색 결과 상태임을 나타내는 UI 요소 검증 ("Search Result" 헤더 등)
        composeTestRule.onNodeWithText("Search Result")
            .assertIsDisplayed()

        // 결과 개수 텍스트 확인
        composeTestRule.onNodeWithText("${MockRecipeRepositoryImpl.mockRecipes.size} results")
            .assertIsDisplayed()
    }

    @Test
    fun search_to_detail_scenario() {
        val recipeRepository = MockRecipeRepositoryImpl
        val profileRepository = MockProfileRepositoryImpl
        val ingredientRepository = MockIngredientRepositoryImpl
        val procedureRepository = MockProcedureRepositoryImpl
        val getRecipeDetailsUseCase = GetRecipeDetailsUseCase(
            recipeRepository, profileRepository, ingredientRepository, procedureRepository,
        )
        val toggleBookmarkUseCase = ToggleBookmarkUseCase(MockBookmarkRepositoryImpl)
        val copyManager = MockCopyManager()

        composeTestRule.setContent {
            var currentScreen by remember { mutableStateOf("search") }
            var selectedRecipeId by remember { mutableStateOf<Long?>(null) }
            val searchViewModel = remember { SearchRecipesViewModel(recipeRepository) }
            val searchUiState by searchViewModel.uiState.collectAsState()

            LaunchedEffect(searchViewModel.event) {
                searchViewModel.event.collect { event ->
                    if (event is SearchRecipesEvent.NavigateToDetails) {
                        selectedRecipeId = event.recipeId
                        currentScreen = "detail"
                    }
                }
            }

            if (currentScreen == "search") {
                SearchRecipesScreen(
                    uiState = searchUiState,
                    onAction = searchViewModel::onAction,
                )
            } else if (currentScreen == "detail") {
                val detailViewModel = remember(selectedRecipeId) {
                    RecipeDetailsViewModel(
                        selectedRecipeId!!,
                        getRecipeDetailsUseCase,
                        toggleBookmarkUseCase,
                        copyManager,
                    )
                }
                val detailUiState by detailViewModel.uiState.collectAsState()

                LaunchedEffect(detailViewModel.event) {
                    detailViewModel.event.collect { event ->
                        if (event is RecipeDetailsEvent.NavigateToBack) {
                            currentScreen = "search"
                        }
                    }
                }

                RecipeDetailsScreen(
                    uiState = detailUiState,
                    onAction = detailViewModel::onAction,
                )
            }
        }

        // 1. Search
        composeTestRule.onNode(hasSetTextAction())
            .performTextInput("Traditional")

        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.onAllNodesWithText("Traditional spare ribs baked").fetchSemanticsNodes()
                .isNotEmpty()
        }

        // 2. Click Item
        composeTestRule.onNodeWithText("Traditional spare ribs baked")
            .performClick()

        // 3. Verify Detail Screen
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.onAllNodesWithContentDescription("back icon").fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule.onNodeWithText("Traditional spare ribs baked")
            .assertIsDisplayed()

        // 4. Back
        composeTestRule.onNodeWithContentDescription("back icon")
            .performClick()

        // 5. Verify Search Screen
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.onAllNodesWithText("Search Result").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("Traditional spare ribs baked")
            .assertIsDisplayed()
    }
}