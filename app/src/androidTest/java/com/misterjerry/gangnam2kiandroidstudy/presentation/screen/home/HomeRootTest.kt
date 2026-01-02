package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import com.misterjerry.gangnam2kiandroidstudy.domain.model.Ingredient
import com.misterjerry.gangnam2kiandroidstudy.domain.model.Ingredients
import com.misterjerry.gangnam2kiandroidstudy.domain.model.Recipe
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.RecipesRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class HomeRootTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `레시피_저장_테스트`() {
        // 1. 가짜 레포지토리 및 뷰모델 설정
        val fakeRecipe = Recipe(
            category = "All",
            chef = "Chef",
            id = 1,
            image = "",
            ingredients = listOf(Ingredients(100, Ingredient(1, "", "Meat"))),
            name = "Test Recipe",
            rating = 5.0,
            time = "10 min",
            isSaved = false
        )
        val fakeRepository = object : RecipesRepository {
            override suspend fun getAllRecipes(): List<Recipe> = listOf(fakeRecipe)
            override suspend fun deleteSavedRecipe(id: Int) {}
        }

        val fakeDao = object : com.misterjerry.gangnam2kiandroidstudy.data.saved_recipes.SavedRecipesDao {
            override suspend fun getSavedRecipesList(): List<com.misterjerry.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity> = emptyList()
            override suspend fun addSavedRecipe(id: Int) {}
            override suspend fun deleteSavedRecipe(id: Int) {}
        }
        val viewModel = HomeViewModel(fakeRepository, fakeDao)

        // 2. 콘텐츠 설정
        composeTestRule.setContent {
            HomeRoot(
                viewModel = viewModel,
                onSearchClicked = {},
                onRecipeItemClicked = {}
            )
        }

        // 데이터가 로드될 때까지 대기 (UI에 레시피가 표시될 때까지)
        // 레시피 이름은 "Test Recipe"이나 클릭 대상은 북마크 아이콘임.
        // MediumRecipeCard의 저장 버튼은 contentDescription이 "inactive"로 설정된 Box임.
        
        // 초기 상태 확인: isSaved = false
        // UI 아이콘의 "inactive" 설명은 바뀌지 않으므로(틴트만 변경됨) 뷰모델 상태를 통해 검증함.
        composeTestRule.waitForIdle()
        assertFalse(viewModel.state.value.resultRecipes.find { it.id == 1 }?.isSaved == true)

        // 3. 클릭 동작 수행
        // contentDescription이 "inactive"인 저장 버튼을 찾음.
        // HomeScreen은 resultRecipes를 MediumRecipeCard와 NewRecipesCard 두 곳에서 사용하므로 중복될 수 있음.
        // 여기서는 첫 번째로 발견되는 MediumRecipeCard의 버튼을 클릭함.
        
        composeTestRule.onAllNodes(hasContentDescription("inactive"))[0].performClick()
        composeTestRule.waitForIdle()

        // 4. 결과 확인: isSaved = true
        assertTrue(viewModel.state.value.resultRecipes.find { it.id == 1 }?.isSaved == true)

        // 5. 다시 클릭 (저장 취소)
        composeTestRule.onAllNodes(hasContentDescription("inactive"))[0].performClick()
        composeTestRule.waitForIdle()

        // 6. 결과 확인: isSaved = false
        assertFalse(viewModel.state.value.resultRecipes.find { it.id == 1 }?.isSaved == true)
    }

    @Test
    fun `화면_회전_테스트`() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)

        val fakeRecipe = Recipe(
            category = "All",
            chef = "Chef",
            id = 1,
            image = "",
            ingredients = listOf(Ingredients(100, Ingredient(1, "", "Meat"))),
            name = "Test Recipe",
            rating = 5.0,
            time = "10 min",
            isSaved = false
        )
        val fakeRepository = object : RecipesRepository {
            override suspend fun getAllRecipes(): List<Recipe> = listOf(fakeRecipe)
            override suspend fun deleteSavedRecipe(id: Int) {}
        }
        val fakeDao = object : com.misterjerry.gangnam2kiandroidstudy.data.saved_recipes.SavedRecipesDao {
            override suspend fun getSavedRecipesList(): List<com.misterjerry.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity> = emptyList()
            override suspend fun addSavedRecipe(id: Int) {}
            override suspend fun deleteSavedRecipe(id: Int) {}
        }
        val viewModel = HomeViewModel(fakeRepository, fakeDao)

        stateRestorationTester.setContent {
            HomeRoot(
                viewModel = viewModel,
                onSearchClicked = {},
                onRecipeItemClicked = {}
            )
        }

        composeTestRule.waitForIdle()
        
        // 저장 버튼 클릭
        composeTestRule.onAllNodes(hasContentDescription("inactive"))[0].performClick()
        composeTestRule.waitForIdle()
        assertTrue(viewModel.state.value.resultRecipes.find { it.id == 1 }?.isSaved == true)

        // 화면 회전 시뮬레이션 (상태 저장 및 복구)
        stateRestorationTester.emulateSavedInstanceStateRestore()
        composeTestRule.waitForIdle()

        // 상태가 여전히 유지되는지 확인
        assertTrue(viewModel.state.value.resultRecipes.find { it.id == 1 }?.isSaved == true)
        
        // 회전 후에도 UI 상호작용이 잘 되는지 확인
        composeTestRule.onAllNodes(hasContentDescription("inactive"))[0].performClick()
        composeTestRule.waitForIdle()
        assertFalse(viewModel.state.value.resultRecipes.find { it.id == 1 }?.isSaved == true)
    }

    @Test
    fun `목록이_비었을_때_레시피_없음_표시_테스트`() {
        val fakeRepository = object : RecipesRepository {
            override suspend fun getAllRecipes(): List<Recipe> = emptyList()
            override suspend fun deleteSavedRecipe(id: Int) {}
        }

        val fakeDao = object : com.misterjerry.gangnam2kiandroidstudy.data.saved_recipes.SavedRecipesDao {
            override suspend fun getSavedRecipesList(): List<com.misterjerry.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity> = emptyList()
            override suspend fun addSavedRecipe(id: Int) {}
            override suspend fun deleteSavedRecipe(id: Int) {}
        }
        val viewModel = HomeViewModel(fakeRepository, fakeDao)

        composeTestRule.setContent {
            HomeRoot(
                viewModel = viewModel,
                onSearchClicked = {},
                onRecipeItemClicked = {}
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("레시피 없음").assertExists()
    }
}