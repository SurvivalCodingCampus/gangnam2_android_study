package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.recipe.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.CategoryFilter
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.RateFilter
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.bottomsheet.TimeFilter
import com.survivalcoding.gangnam2kiandroidstudy.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchRecipeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SearchRecipeViewModel
    private lateinit var fakeRepository: FakeRecipeRepository

    private val recipe1 = Recipe(id = 1, name = "Kimchi Stew", createdAt = 100L, rating = 4.5, category = "Korean", imageUrl = "", chef = "", time = "30m", address = "Seoul")
    private val recipe2 = Recipe(id = 2, name = "Spaghetti", createdAt = 200L, rating = 4.8, category = "Italian", imageUrl = "", chef = "", time = "20m", address = "Seoul")
    private val recipe3 = Recipe(id = 3, name = "Kimchi Fried Rice", createdAt = 50L, rating = 4.2, category = "Korean", imageUrl = "", chef = "", time = "15m", address = "Seoul")


    @Before
    fun setUp() {
        fakeRepository = FakeRecipeRepository()
        viewModel = SearchRecipeViewModel(fakeRepository)

        fakeRepository.addRecipe(recipe1)
        fakeRepository.addRecipe(recipe2)
        fakeRepository.addRecipe(recipe3)
    }

    @After
    fun tearDown() {
        fakeRepository.clear()
    }

    private fun applyFilter(filterState: FilterSearchState) {
        viewModel.updateFilterSearchState(filterState)
        viewModel.applyFilter()
    }

    @Test
    fun `getState initial state verification`() = runTest {
        val initialState = viewModel.state.value
        assertThat(initialState).isEqualTo(SearchRecipeState())
    }

    @Test
    fun `getEvent no initial events`() = runTest {
        viewModel.event.test {
            expectNoEvents()
        }
    }

    @Test
    fun `onAction Load success`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)

        val state = viewModel.state.value
        assertThat(state.allRecipes).hasSize(3)
        assertThat(state.allRecipes).containsExactly(recipe1, recipe2, recipe3)
        assertThat(state.filteredRecipes).hasSize(3)
        assertThat(state.filteredRecipesText).isEqualTo("3 results")
    }

    @Test
    fun `onAction Load with empty repository`() = runTest {
        fakeRepository.shouldReturnEmpty = true
        viewModel.onAction(SearchRecipeAction.Load)

        val state = viewModel.state.value
        assertThat(state.allRecipes).isEmpty()
        assertThat(state.filteredRecipes).isEmpty()
        assertThat(state.filteredRecipesText).isEqualTo("0 results")
    }

    @Test
    fun `onAction InputKeyword state update`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        viewModel.onAction(SearchRecipeAction.InputKeyword("Kimchi"))

        val state = viewModel.state.value
        assertThat(state.searchKeyword).isEqualTo("Kimchi")
        assertThat(state.filteredRecipes).hasSize(2)
        assertThat(state.filteredRecipes).containsExactly(recipe1, recipe3)
        assertThat(state.filteredRecipesText).isEqualTo("2 results")
    }

    @Test
    fun `integration test for search and display`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        assertThat(viewModel.state.value.filteredRecipes).hasSize(3)

        val keyword = "Spaghetti"
        viewModel.onAction(SearchRecipeAction.InputKeyword(keyword))

        val state = viewModel.state.value
        assertThat(state.searchKeyword).isEqualTo(keyword)
        assertThat(state.filteredRecipes).hasSize(1)
        assertThat(state.filteredRecipes.first().name).isEqualTo("Spaghetti")
        assertThat(state.filteredRecipesText).isEqualTo("1 results")
    }

    @Test
    fun `onAction ApplyFilter event emission`() = runTest {
        viewModel.event.test {
            viewModel.onAction(SearchRecipeAction.ApplyFilter)
            val event = awaitItem()
            assertThat(event).isInstanceOf(SearchRecipeEvent.ShowSnackBar::class.java)
            assertThat((event as SearchRecipeEvent.ShowSnackBar).message).isEqualTo("필터가 적용되었습니다.")
        }
    }

    @Test
    fun `onAction CancelFilter state reset and filter application`() = runTest {
        val initialFilterState = FilterSearchState()
        viewModel.updateFilterSearchState(initialFilterState.copy(category = CategoryFilter.DINNER))

        viewModel.event.test {
            viewModel.onAction(SearchRecipeAction.CancelFilter(initialFilterState))

            val state = viewModel.state.value
            assertThat(state.filterState).isEqualTo(initialFilterState)

            val event = awaitItem()
            assertThat(event).isInstanceOf(SearchRecipeEvent.ShowSnackBar::class.java)
            assertThat((event as SearchRecipeEvent.ShowSnackBar).message).isEqualTo("필터가 취소되었습니다.")
        }
    }

    @Test
    fun `updateSearchQuery with new keyword`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        viewModel.updateSearchQuery("Stew")

        val state = viewModel.state.value
        assertThat(state.searchKeyword).isEqualTo("Stew")
        assertThat(state.filteredRecipes).hasSize(1)
        assertThat(state.filteredRecipes.first()).isEqualTo(recipe1)
    }

    @Test
    fun `updateSearchQuery with empty keyword`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        viewModel.updateSearchQuery("something")
        viewModel.updateSearchQuery("")

        val state = viewModel.state.value
        assertThat(state.searchKeyword).isEmpty()
        assertThat(state.filteredRecipes).hasSize(3)
    }

    @Test
    fun `updateSearchQuery with whitespace keyword`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        viewModel.updateSearchQuery("   ")

        val state = viewModel.state.value
        assertThat(state.searchKeyword).isEqualTo("   ")
        assertThat(state.filteredRecipes).hasSize(3)
    }

    @Test
    fun `updateFilterSearchState updates state`() {
        val newFilterState = FilterSearchState(category = CategoryFilter.DINNER)
        viewModel.updateFilterSearchState(newFilterState)
        assertThat(viewModel.state.value.filterState).isEqualTo(newFilterState)
    }

    @Test
    fun `applyFilter keyword matching`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        viewModel.updateSearchQuery("kimchi")

        val state = viewModel.state.value
        assertThat(state.filteredRecipes).hasSize(2)
        assertThat(state.filteredRecipes).containsExactly(recipe1, recipe3)
    }

    @Test
    fun `applyFilter keyword no match`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        viewModel.updateSearchQuery("nonexistent")

        val state = viewModel.state.value
        assertThat(state.filteredRecipes).isEmpty()
        assertThat(state.filteredRecipesText).isEqualTo("0 results")
    }

    @Test
    fun `applyFilter with TimeFilter NEWEST`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        applyFilter(FilterSearchState(time = TimeFilter.NEWEST))

        val state = viewModel.state.value
        assertThat(state.filteredRecipes).containsExactly(recipe2, recipe1, recipe3).inOrder()
    }

    @Test
    fun `applyFilter with TimeFilter OLDEST`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        applyFilter(FilterSearchState(time = TimeFilter.OLDEST))

        val state = viewModel.state.value
        assertThat(state.filteredRecipes).containsExactly(recipe3, recipe1, recipe2).inOrder()
    }

    @Test
    fun `applyFilter with TimeFilter POPULARITY`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        applyFilter(FilterSearchState(time = TimeFilter.POPULARITY))

        val state = viewModel.state.value
        assertThat(state.filteredRecipes).containsExactly(recipe2, recipe1, recipe3).inOrder()
    }

    @Test
    fun `applyFilter with minimum rating`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        applyFilter(FilterSearchState(rate = RateFilter.FOUR))

        val state = viewModel.state.value
        assertThat(state.filteredRecipes).hasSize(3)

        applyFilter(FilterSearchState(rate = RateFilter.FIVE))

        val state2 = viewModel.state.value
        assertThat(state2.filteredRecipes).isEmpty()
    }

    @Test
    fun `applyFilter rating filter with invalid label`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        // RateFilter is an enum of strings, so it can't have an invalid label that's not a float
        // This test is no longer applicable in the same way.
        // We will test that filtering by rating works as expected.
        applyFilter(FilterSearchState(rate = RateFilter.FOUR))

        val state = viewModel.state.value
        assertThat(state.filteredRecipes.all { it.rating >= 4.0 }).isTrue()
        assertThat(state.filteredRecipes).hasSize(3)
    }

    @Test
    fun `applyFilter with specific category`() = runTest {
        val koreanRecipe = Recipe(1, "Korean", "Bibimbap", "", "", "25m", 4.5, 10,false, "Seoul")
        fakeRepository.addRecipe(koreanRecipe)
        viewModel.onAction(SearchRecipeAction.Load)

        applyFilter(FilterSearchState(category = CategoryFilter.JAPANESE))

        val state = viewModel.state.value
        assertThat(state.filteredRecipes).isEmpty()
    }

    @Test
    fun `applyFilter with CategoryFilter ALL`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        applyFilter(FilterSearchState(category = CategoryFilter.ALL))

        val state = viewModel.state.value
        assertThat(state.filteredRecipes).hasSize(3)
    }

    @Test
    fun `applyFilter with combined filters`() = runTest {
        val koreanRecipe = Recipe(4, "Korean", "Kimchi Pancake", "", "", "20m", 4.7, 150,true,"Seoul")
        fakeRepository.addRecipe(koreanRecipe)
        viewModel.onAction(SearchRecipeAction.Load)
        viewModel.updateSearchQuery("Kimchi")
        applyFilter(FilterSearchState(
            rate = RateFilter.FOUR,
            time = TimeFilter.OLDEST,
            category = CategoryFilter.CHINESE
        ))

        val state = viewModel.state.value
        assertThat(state.filteredRecipes).isEmpty()
    }

    @Test
    fun `applyFilter on empty recipe list`() = runTest {
        fakeRepository.shouldReturnEmpty = true
        viewModel.onAction(SearchRecipeAction.Load)
        viewModel.applyFilter()

        val state = viewModel.state.value
        assertThat(state.allRecipes).isEmpty()
        assertThat(state.filteredRecipes).isEmpty()
        assertThat(state.filteredRecipesText).isEqualTo("0 results")
    }

    @Test
    fun `applyFilter with no active filters`() = runTest {
        viewModel.onAction(SearchRecipeAction.Load)
        viewModel.applyFilter()

        val state = viewModel.state.value
        assertThat(state.filteredRecipes).hasSize(3)
        assertThat(state.filteredRecipes).containsExactly(recipe1, recipe2, recipe3)
    }
}
