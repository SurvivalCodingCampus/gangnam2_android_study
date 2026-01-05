package com.misterjerry.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.misterjerry.gangnam2kiandroidstudy.domain.model.SavedRecipesEntity
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class SavedRecipesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        val testModule = module {
            single<SavedRecipesRepository> {
                object : SavedRecipesRepository {
                    override suspend fun getSavedRecipes(): List<SavedRecipesEntity> {
                        return listOf(
                            SavedRecipesEntity(recipeId = 1),
                            SavedRecipesEntity(recipeId = 2)
                        )
                    }

                    override suspend fun deleteSavedRecipe(id: Int) {
                        // 테스트 동작을 위해 비워둠
                    }

                    override suspend fun addSavedRecipe(id: Int) {
                        // 테스트 동작을 위해 비워둠
                    }
                }
            }
        }
        loadKoinModules(testModule)
    }

    @Test
    fun `저장된_레시피_표시_테스트`() {
        composeTestRule.setContent {
            SavedRecipesRoot()
        }

        composeTestRule.onNodeWithText("Traditional spare ribs baked").assertIsDisplayed()
        composeTestRule.onNodeWithText("Spice roasted chicken with flavored rice").assertIsDisplayed()
    }

    @Test
    fun `북마크_클릭_시_레시피_삭제_테스트`() {
        composeTestRule.setContent {
            SavedRecipesRoot()
        }

        // Verify item exists
        composeTestRule.onNodeWithText("Traditional spare ribs baked").assertIsDisplayed()

        // Click the first bookmark icon
        composeTestRule.onAllNodesWithContentDescription("Bookmark")
            .onFirst()
            .performClick()

        // Verify item is removed
        composeTestRule.onNodeWithText("Traditional spare ribs baked").assertIsNotDisplayed()
    }
}