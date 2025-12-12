package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.selector.HomeCategory
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: HomeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val fakeRepository = FakeRecipeRepository(RecipeTestFixtures.fakeRecipes)
        viewModel = HomeViewModel(fakeRepository)
    }


    @Test
    fun `init 초기 로드에서 recipes 와 filteredRecipes 에 전체 리스트`() = runTest {
        // 코루틴 실행 완료되도록
        advanceUntilIdle()

        val state = viewModel.state.value

        assertEquals(RecipeTestFixtures.fakeRecipes.size, state.recipes.size)
        assertEquals(RecipeTestFixtures.fakeRecipes.size, state.filteredRecipes.size)
    }

    @Test
    fun `Cereal 선택 시 Cereal 카테고리만 필터링`() = runTest {
        advanceUntilIdle()

        viewModel.onSelectCategory(HomeCategory.CEREAL)

        val state = viewModel.state.value

        assertEquals(HomeCategory.CEREAL, state.selectedCategory)
        assertEquals("Cereal", state.filteredRecipes.first().category)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}