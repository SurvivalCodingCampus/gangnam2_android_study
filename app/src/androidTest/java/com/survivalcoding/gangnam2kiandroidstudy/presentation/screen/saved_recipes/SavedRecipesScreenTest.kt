package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes

import android.content.Context
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.local.AppDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.bookmark.repository.RoomBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.SavedRecipesRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.recipe.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SavedRecipesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var database: AppDatabase
    private lateinit var bookmarkRepository: RoomBookmarkRepositoryImpl
    private lateinit var fakeRecipeRepository: FakeRecipeRepository
    private lateinit var viewModel: SavedRecipesViewModel

    private val recipe1 = Recipe(
        id = 1,
        name = "Recipe 1",
        category = "Indian",
        time = "30 min",
        chef = "Chef 1",
        rating = 4.5,
        imageUrl = "",
        createdAt = 1L,
        address = "Seoul",
    )

    private val recipe2 = Recipe(
        id = 2,
        name = "Recipe 2",
        category = "Korean",
        time = "20 min",
        chef = "Chef 2",
        rating = 4.7,
        imageUrl = "",
        createdAt = 2L,
        address = "Busan",
    )

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        bookmarkRepository = RoomBookmarkRepositoryImpl(database.bookmarkDao())

        fakeRecipeRepository = FakeRecipeRepository().apply {
            addRecipe(recipe1)
            addRecipe(recipe2)
        }

        val savedRecipesRepository = SavedRecipesRepositoryImpl(fakeRecipeRepository)
        val getSavedRecipesUseCase = GetSavedRecipesUseCase(
            bookmarkRepository = bookmarkRepository,
            savedRecipesRepository = savedRecipesRepository,
        )

        viewModel = SavedRecipesViewModel(
            getSavedRecipesUseCase = getSavedRecipesUseCase,
            bookmarkRepository = bookmarkRepository,
        )
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun savedRecipes_showsOnlyBookmarkedRecipes() = runBlocking {
        bookmarkRepository.addBookmark(recipe1.id)

        composeTestRule.setContent {
            SavedRecipesScreen(
                state = viewModel.state.collectAsState().value,
                listState = rememberLazyListState(),
                onRemoveBookmark = viewModel::removeBookmark,
                onCardClick = {},
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Saved Recipes").assertExists()
        composeTestRule.onNodeWithText(recipe1.name).assertExists()
        composeTestRule.onNodeWithText(recipe2.name).assertDoesNotExist()
    }

    @Test
    fun removeBookmark_updatesUiAndDatabase() = runBlocking {
        bookmarkRepository.addBookmark(recipe1.id)

        composeTestRule.setContent {
            SavedRecipesScreen(
                state = viewModel.state.collectAsState().value,
                listState = rememberLazyListState(),
                onRemoveBookmark = viewModel::removeBookmark,
                onCardClick = {},
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule
            .onAllNodesWithContentDescription("bookmark Recipe")
            .onFirst()
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText(recipe1.name).assertDoesNotExist()

        val bookmarkedIds = database.bookmarkDao()
            .getAllBookmarkedRecipeIds()
            .first()
        assertThat(bookmarkedIds).doesNotContain(recipe1.id)
    }
}
