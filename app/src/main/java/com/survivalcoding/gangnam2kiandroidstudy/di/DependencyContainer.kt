package com.survivalcoding.gangnam2kiandroidstudy.di

import android.content.Context
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AppAssetManagerImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.IngredientDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.IngredientDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail.RecipeDetailViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.SearchViewModel

// 임의의 싱글톤 DI 컨테이너
object DependencyContainer {
    fun provideRecipeDataSource(context: Context): RecipeDataSource {
        return RecipeDataSourceImpl.getInstance(
            provideAssetManager(context)
        )
    }

    fun provideIngredientDataSource(context: Context): IngredientDataSource {
        return IngredientDataSourceImpl.getInstance(
            provideAssetManager(context)
        )
    }

    fun provideProcedureDataSource(context: Context): ProcedureDataSource {
        return ProcedureDataSourceImpl.getInstance(
            provideAssetManager(context)
        )
    }

    fun provideBookmarkDataSource(): BookmarkDataSource {
        return BookmarkDataSourceImpl.getInstance()
    }

    fun provideRecipeRepository(context: Context): RecipeRepository {
        return RecipeRepositoryImpl.getInstance(
            provideRecipeDataSource(context)
        )
    }

    fun provideIngredientRepository(context: Context): IngredientRepository {
        return IngredientRepositoryImpl.getInstance(
            provideIngredientDataSource(context)
        )
    }

    fun provideProcedureRepository(context: Context): ProcedureRepository {
        return ProcedureRepositoryImpl.getInstance(
            provideProcedureDataSource(context)
        )
    }

    fun provideBookmarkRepository(): BookmarkRepository {
        return BookmarkRepositoryImpl.getInstance(
            provideBookmarkDataSource()
        )
    }

    fun provideAssetManager(context: Context) =
        AppAssetManagerImpl(context.assets)

    fun provideSavedRecipesViewModelFactory(context: Context) = viewModelFactory {
        initializer {
            SavedRecipesViewModel(
                recipeRepository = provideRecipeRepository(context.applicationContext),
                bookmarkRepository = provideBookmarkRepository()
            )
        }
    }

    fun provideSearchViewModelFactory(context: Context) = viewModelFactory {
        initializer {
            SearchViewModel(
                recipeRepository = provideRecipeRepository(context.applicationContext)
            )
        }
    }

    fun provideHomeViewModelFactory(context: Context) = viewModelFactory {
        initializer {
            HomeViewModel(
                recipeRepository = provideRecipeRepository(context.applicationContext)
            )
        }
    }

    fun provideRecipeDetailViewModelFactory(context: Context, route: Route.RecipeDetails) = viewModelFactory {
        initializer {
            val savedStateHandle = createSavedStateHandle()
            savedStateHandle["recipeId"] = route.recipeId
            RecipeDetailViewModel(
                recipeRepository = provideRecipeRepository(context.applicationContext),
                ingredientRepository = provideIngredientRepository(context.applicationContext),
                procedureRepository = provideProcedureRepository(context.applicationContext),
                savedStateHandle
            )
        }
    }
}