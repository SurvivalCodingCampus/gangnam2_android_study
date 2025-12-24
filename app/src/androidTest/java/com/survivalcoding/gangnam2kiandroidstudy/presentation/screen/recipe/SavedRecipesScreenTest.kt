package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.HomeImage
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import org.junit.Rule
import org.junit.Test

class SavedRecipesScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun scrollToBottom_triggersReachedBottomAction() {
        // given
        var reachedBottomCalled = false

        composeRule.setContent {
            SavedRecipesScreen(
                state = SavedRecipesState(
                    recipes = fakeRecipes,
                    bookmarkedIds = emptySet()
                ),
                onAction = { action ->
                    if (action is SavedRecipesAction.ReachedBottom) {
                        reachedBottomCalled = true
                    }
                }
            )
        }

        // when: 리스트 끝까지 스크롤
        composeRule
            .onNodeWithTag("SavedRecipesList")
            .performScrollToIndex(fakeRecipes.lastIndex)

        // then
        composeRule.runOnIdle {
            assert(reachedBottomCalled)
        }
    }
}

private val fakeRecipes = List(20) { index ->
    Recipe(
        id = index,
        title = "Recipe $index",
        chefId = index,
        chefName = "Chef John",
        time = "10 min",
        rating = 4.5,
        imageUrls = "",
        createdAt = 1736034600000,
        category = "lunch",
        homeImage = HomeImage.FOOD1
    )
}
