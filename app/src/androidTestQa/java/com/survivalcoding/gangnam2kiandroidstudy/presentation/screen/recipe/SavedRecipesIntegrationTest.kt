package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe

import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.survivalcoding.gangnam2kiandroidstudy.MainActivity
import com.survivalcoding.gangnam2kiandroidstudy.core.di.NetworkModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.RepositoryModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.RoomModule
import com.survivalcoding.gangnam2kiandroidstudy.data.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.database.AppDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.entity.BookmarkEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkEvent
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

@HiltAndroidTest
@UninstallModules(RoomModule::class, RepositoryModule::class, NetworkModule::class)
class SavedRecipesIntegrationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var bookmarkDao: BookmarkDao

    @Module
    @InstallIn(SingletonComponent::class)
    object TestModule {

        @Provides
        @Singleton
        fun provideInMemoryDb(): AppDatabase {
            return Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase::class.java
            ).allowMainThreadQueries().build()
        }

        @Provides
        @Singleton
        fun provideBookmarkDao(database: AppDatabase): BookmarkDao {
            return database.bookmarkDao()
        }

        @Provides
        @Singleton
        fun provideBookmarkRepository(bookmarkDao: BookmarkDao): BookmarkRepository {
            return BookmarkRepositoryImpl(bookmarkDao)
        }

        @Provides
        @Singleton
        fun provideRecipeRepository(): RecipeRepository {
            return FakeRecipeRepository()
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
    }

    @Test
    fun testBookmarksWithInMemoryDb() = runBlocking {
        // 1. Insert a bookmark directly into DB
        val recipeId = 1
        bookmarkDao.insert(BookmarkEntity(recipeId))

        // 2. Launch Screen
        composeRule.activity.setContent {
            val viewModel: SavedRecipesViewModel = hiltViewModel()
            val state = viewModel.state.collectAsState().value
            SavedRecipesScreen(state = state, onAction = viewModel::onAction)
        }

        composeRule.waitForIdle()

        // 3. Verify item is displayed
        // "Traditional spare ribs baked" is the title in FakeRecipeRepository for id 1
        composeRule.onNodeWithText("Traditional spare ribs baked").assertIsDisplayed()

        // 4. Click Bookmark to remove
        composeRule.onNodeWithContentDescription("Bookmark Filled").performClick()

        // 5. Verify item disappears
        composeRule.waitUntil(timeoutMillis = 3000) {
            composeRule.onAllNodesWithText("Traditional spare ribs baked").fetchSemanticsNodes()
                .isEmpty()
        }

        composeRule.onNodeWithText("Traditional spare ribs baked").assertIsNotDisplayed()

        // 6. Verify DB is empty
        assert(bookmarkDao.getAllIds().first().isEmpty())
    }
}
