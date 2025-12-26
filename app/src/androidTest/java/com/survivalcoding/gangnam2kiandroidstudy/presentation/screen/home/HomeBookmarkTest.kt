package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasStateDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.module.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class HomeBookmarkTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            modules(
                module {
                    single<RecipeRepository> { FakeRecipeRepository() }
                    viewModel { HomeViewModel(get()) }
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun bookmark_click_updates_state_and_persists_after_recomposition() {
        // Given: HomeRoot 화면을 설정합니다.
        composeTestRule.setContent {
            HomeRoot(
                onNavigateToSearch = {},
                onNavigateToProfile = {},
                onNavigateToRecipeDetail = {}
            )
        }

        // ComposeRule은 UI가 유휴(idle) 상태가 될 때까지 자동으로 기다립니다.
        
        val bookmarkButton = composeTestRule.onNodeWithContentDescription("Bookmark Button")
        
        // 초기 상태 확인: 북마크가 되어 있지 않아야 함
        bookmarkButton.assert(hasStateDescription("Not Saved"))

        // When: 북마크 버튼을 클릭함
        bookmarkButton.performClick()

        // Then: 상태가 "Saved"로 변경되었는지 확인
        bookmarkButton.assert(hasStateDescription("Saved"))

        // When: 다시 클릭함 (토글)
        bookmarkButton.performClick()

        // Then: 다시 "Not Saved" 상태로 돌아왔는지 확인
        bookmarkButton.assert(hasStateDescription("Not Saved"))
    }
}

class FakeRecipeRepository : RecipeRepository {
    override suspend fun findRecipe(id: Long): Result<Recipe, String> {
        return Result.Error("Not implemented")
    }

    override suspend fun findRecipes(): Result<List<Recipe>, String> {
        return Result.Success(
            listOf(
                Recipe(
                    id = 1,
                    category = RecipeCategory.ASIAN,
                    name = "Test Recipe",
                    imageUrl = "",
                    chef = "Chef",
                    time = "20 min",
                    rating = 5.0,
                    ingredients = emptyList()
                )
            )
        )
    }
}