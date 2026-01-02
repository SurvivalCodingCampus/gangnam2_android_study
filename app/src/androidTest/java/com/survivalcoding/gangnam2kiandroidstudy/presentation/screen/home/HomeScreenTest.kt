package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import com.survivalcoding.gangnam2kiandroidstudy.TestActivity
import com.survivalcoding.gangnam2kiandroidstudy.core.di.NetworkModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.RepositoryModule
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkEvent
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkStatus
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

@HiltAndroidTest
@UninstallModules(RepositoryModule::class, NetworkModule::class)
class HomeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<TestActivity>()

    @Inject
    lateinit var repository: RecipeRepository

    @Module
    @InstallIn(SingletonComponent::class)
    object TestRepositoryModule {

        @Provides
        @Singleton
        fun provideRecipeRepository(): RecipeRepository {
            return FakeRecipeRepository()
        }

        @Provides
        @Singleton
        fun provideBookmarkRepository(): BookmarkRepository {
            return object : BookmarkRepository {
                override fun getSavedRecipeIds(): Flow<List<Int>> = flowOf(emptyList())
                override suspend fun addBookmark(recipeId: Int) {}
                override suspend fun removeBookmark(recipeId: Int) {}
                override suspend fun isBookmarked(recipeId: Int): Boolean = false
            }
        }

        @Provides
        @Singleton
        fun provideChefRepository(): ChefRepository {
            return object : ChefRepository {
                override suspend fun getChefById(id: Int): Chef? = null
            }
        }

        @Provides
        @Singleton
        fun provideIngredientRepository(): IngredientRepository {
            return object : IngredientRepository {
                override suspend fun getIngredientsByRecipeId(recipeId: Int): List<IngredientItem> =
                    emptyList()
            }
        }

        @Provides
        @Singleton
        fun provideProcedureRepository(): ProcedureRepository {
            return object : ProcedureRepository {
                override suspend fun getProceduresByRecipeId(recipeId: Int): List<Procedure> =
                    emptyList()
            }
        }

        @Provides
        @Singleton
        fun provideNetworkMonitor(): NetworkMonitor {
            return object : NetworkMonitor {
                override val events: Flow<NetworkEvent> = flow { }
                override val status: StateFlow<NetworkStatus> =
                    MutableStateFlow(NetworkStatus.Available)
            }
        }
    }

    @Before
    fun setup() {
        hiltRule.inject()
        // Reset state
        (repository as FakeRecipeRepository).setShouldThrowError(false)
    }

    @Test
    fun homeScreen_displaysData_whenRepositorySucceeds() {
        composeRule.runOnUiThread {
            composeRule.activity.setContent {
                val viewModel: HomeViewModel = hiltViewModel()
                val state = viewModel.state.collectAsState().value
                HomeScreen(
                    state = state,
                    onAction = viewModel::onAction,
                    onNavigateToSearch = {},
                    onRecipeClick = {}
                )
            }
        }

        composeRule.waitForIdle()

        // Check for items from FakeRecipeRepository
        // "Traditional spare ribs baked"
        composeRule.onNodeWithText("Traditional spare ribs baked").assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysError_whenRepositoryFails() {
        // Set error state BEFORE launching
        (repository as FakeRecipeRepository).setShouldThrowError(true)

        composeRule.runOnUiThread {
            composeRule.activity.setContent {
                val viewModel: HomeViewModel = hiltViewModel()
                val state = viewModel.state.collectAsState().value
                HomeScreen(
                    state = state,
                    onAction = viewModel::onAction,
                    onNavigateToSearch = {},
                    onRecipeClick = {}
                )
            }
        }

        composeRule.waitForIdle()

        // Verify error message
        composeRule
            .onNodeWithText("Failed to load data")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Traditional spare ribs baked")
            .assertDoesNotExist()
    }
}
