package com.survivalcoding.gangnam2kiandroidstudy.presentation.search

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.survivalcoding.gangnam2kiandroidstudy.core.di.viewModelModule
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class SearchIntegrationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        stopKoin()
        startKoin {
            modules(
                viewModelModule,
                module {
                    single<RecipeRepository> { FakeRecipeRepository() }
                }
            )
        }
    }

    @Test
    fun search_recipe_and_display_result() {
        // Given
        composeTestRule.setContent {
            SearchRecipeScreenRoot()
        }

        // When
        composeTestRule.onNodeWithText("Search Recipe") // Placeholder
            .performTextInput("Traditional")

        // Then
        // Wait for results.
        // The FakeRepository has "Traditional spare ribs baked".
        // It has a 1s delay.
        // Debounce is 300ms.
        
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("Traditional spare ribs baked")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("Traditional spare ribs baked")
            .assertIsDisplayed()
    }
}
