package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.compose.koinViewModel
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class HomeScreenTest : KoinTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val bookmarkRepository: BookmarkRepository by inject()

    @Test
    fun testBookmarkFunctionality() = runTest {
        composeTestRule.setContent {
            val viewModel: HomeViewModel = koinViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            
            HomeScreen(
                state = state,
                onAction = viewModel::onAction
            )
        }

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithContentDescription("Bookmark Recipe")
                .fetchSemanticsNodes().isNotEmpty()
        }

        val bookmarkButton = composeTestRule
            .onAllNodesWithContentDescription("Bookmark Recipe")
            .onFirst()

        bookmarkButton.assertIsDisplayed()
        bookmarkButton.performClick()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithContentDescription("Unbookmark Recipe")
                .fetchSemanticsNodes().isNotEmpty()
        }
        
        composeTestRule
            .onNodeWithContentDescription("Unbookmark Recipe")
            .assertIsDisplayed()

        val bookmarkIdsAfterAdd = bookmarkRepository.getBookmarkId()
        assertTrue("DB should contain recipe ID 1 after bookmarking", bookmarkIdsAfterAdd.contains(1))
            
        composeTestRule
            .onNodeWithContentDescription("Unbookmark Recipe")
            .performClick()
            
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithContentDescription("Bookmark Recipe")
                .fetchSemanticsNodes().isNotEmpty()
        }
        
        composeTestRule
             .onAllNodesWithContentDescription("Bookmark Recipe")
             .onFirst()
             .assertIsDisplayed()

        val bookmarkIdsAfterRemove = bookmarkRepository.getBookmarkId()
        assertFalse("DB should not contain recipe ID 1 after unbookmarking", bookmarkIdsAfterRemove.contains(1))
    }
}