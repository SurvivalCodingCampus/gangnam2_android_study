package com.survivalcoding.gangnam2kiandroidstudy.core.di

import android.content.Context
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.AppAssetManagerImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.BookmarkDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.BookmarkDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ChefDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.ChefDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.IngredientDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.IngredientDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.ProcedureDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.local.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ChefRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetAllRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetFilteredRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SearchRecipeByKeywordUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.RemoveBookmarkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.recipedetail.RecipeDetailViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.savedrecipes.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search.SearchViewModel

// 임의의 싱글톤 DI 컨테이너
object DependencyContainer {
    fun provideRecipeDataSource(context: Context): RecipeDataSource {
        return RecipeDataSourceImpl.getInstance(provideAssetManager(context))
    }

    fun provideIngredientDataSource(context: Context): IngredientDataSource {
        return IngredientDataSourceImpl.getInstance(provideAssetManager(context))
    }

    fun provideProcedureDataSource(context: Context): ProcedureDataSource {
        return ProcedureDataSourceImpl.getInstance(provideAssetManager(context))
    }

    fun provideBookmarkDataSource(): BookmarkDataSource {
        return BookmarkDataSourceImpl.getInstance()
    }

    fun provideChefDataSource(context: Context): ChefDataSource {
        return ChefDataSourceImpl.getInstance(provideAssetManager(context))
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

    fun provideChefRepository(context: Context): ChefRepository {
        return ChefRepositoryImpl.getInstance(provideChefDataSource(context))
    }

    fun provideAssetManager(context: Context) =
        AppAssetManagerImpl(context.assets)

    fun provideGetSavedRecipesUseCase(context: Context) = GetSavedRecipesUseCase(
        provideRecipeRepository(context.applicationContext),
        provideBookmarkRepository()
    )

    fun provideRemoveBookmarkUseCase() = RemoveBookmarkUseCase(
        provideBookmarkRepository()
    )

    fun provideGetRecipeDetailsUseCase(context: Context) = GetRecipeDetailsUseCase(
        provideRecipeRepository(context.applicationContext),
        provideChefRepository(context.applicationContext),
        provideIngredientRepository(context.applicationContext),
        provideProcedureRepository(context.applicationContext)
    )

    fun provideGetAllRecipes(context: Context) = GetAllRecipesUseCase(
        provideRecipeRepository(context.applicationContext)
    )

    fun provideGetFilteredRecipeByCategoryUseCase(context: Context) = GetFilteredRecipesUseCase(
        provideRecipeRepository(context.applicationContext)
    )

    fun provideSearchRecipeByKeywordUseCase(context: Context) = SearchRecipeByKeywordUseCase(
        provideRecipeRepository(context.applicationContext)
    )

    fun provideSavedRecipesViewModelFactory(context: Context) = viewModelFactory {
        initializer {
            SavedRecipesViewModel(
                getSavedRecipesUseCase = provideGetSavedRecipesUseCase(context),
                removeBookmarkUseCase = provideRemoveBookmarkUseCase()
            )
        }
    }

    fun provideSearchViewModelFactory(context: Context) = viewModelFactory {
        initializer {
            SearchViewModel(
                getAllRecipesUseCase = provideGetAllRecipes(context),
                searchRecipeByKeywordUseCase = provideSearchRecipeByKeywordUseCase(context)
            )
        }
    }

    fun provideHomeViewModelFactory(context: Context) = viewModelFactory {
        initializer {
            HomeViewModel(
                getFilteredRecipesUseCase = provideGetFilteredRecipeByCategoryUseCase(context)
            )
        }
    }

    fun provideRecipeDetailViewModelFactory(context: Context, route: Route.RecipeDetails) = viewModelFactory {
        initializer {
            val savedStateHandle = createSavedStateHandle()
            savedStateHandle["recipeId"] = route.recipeId
            RecipeDetailViewModel(
                getRecipeDetailsUseCase = provideGetRecipeDetailsUseCase(context),
                savedStateHandle
            )
        }
    }
}