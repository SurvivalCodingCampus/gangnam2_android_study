package com.survivalcoding.gangnam2kiandroidstudy.core.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockUserDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.UserDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.local.AppDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.local.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.local.dao.RecipeDao
import com.survivalcoding.gangnam2kiandroidstudy.data.local.mapper.toEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<UserDataSource> { MockUserDataSourceImpl() }
    single<BookmarkRepository> { BookmarkRepositoryImpl(get()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database_dev"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Seed data
                CoroutineScope(Dispatchers.IO).launch {
                    val appDatabase = get<AppDatabase>()
                    val mockRecipes = MockRecipeDataSourceImpl().mockRecipeDtoList
                    
                    val recipes = mockRecipes.map { it.toEntity() }
                    appDatabase.recipeDao().insertRecipes(recipes)
                    
                    mockRecipes.forEach { recipe ->
                        recipe.ingredients?.let { ingredients ->
                            // Insert Ingredients
                            val ingredientEntities = ingredients.mapNotNull { it.ingredient?.toEntity() }
                            appDatabase.recipeDao().insertIngredients(ingredientEntities)
                            
                            // Insert Join
                            val joins = ingredients.mapNotNull { listDto ->
                                listDto.ingredient?.let { ingredient ->
                                    com.survivalcoding.gangnam2kiandroidstudy.data.local.entity.RecipeIngredientEntity(
                                        recipeId = recipe.id ?: 0,
                                        ingredientId = ingredient.id ?: 0,
                                        amount = listDto.amount ?: 0
                                    )
                                }
                            }
                            appDatabase.recipeDao().insertRecipeIngredients(joins)
                        }
                    }
                }
            }
        }).fallbackToDestructiveMigration().build()
    }

    single<BookmarkDao> {
        get<AppDatabase>().bookmarkDao()
    }
    
    single<RecipeDao> {
        get<AppDatabase>().recipeDao()
    }
}
