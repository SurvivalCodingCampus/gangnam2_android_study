package com.survivalcoding.gangnam2kiandroidstudy.di

import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.datasource.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.SavedRecipesRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe.SearchRecipeViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {

    // DataSource
    single<RecipeDataSource> { RecipeDataSourceImpl() }

    // Repository
    single<RecipeRepository> { RecipeRepositoryImpl(get()) }
    single<BookmarkRepository> { BookmarkRepositoryImpl(get()) }
    single<SavedRecipesRepository> { SavedRecipesRepositoryImpl(get()) }

    // UseCase
    single {
        GetSavedRecipesUseCase(
            bookmarkRepository = get(),
            savedRecipesRepository = get()
        )
    }

    // ViewModel
    viewModel {
        HomeViewModel(
            repository = get()
        )
    }

    viewModel {
        SearchRecipeViewModel(
            repository = get()
        )
    }

    viewModel {
        SavedRecipesViewModel(
            getSavedRecipesUseCase = get(),
            bookmarkRepository = get()
        )
    }
}

