package com.survivalcoding.gangnam2kiandroidstudy.di

import android.content.Context
import android.net.ConnectivityManager
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.datasource.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.network.NetworkMonitorImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.repository.SavedRecipesRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SavedRecipesRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipes.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe.SearchRecipeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Network
    single {
        androidContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    single<NetworkMonitor> {
        NetworkMonitorImpl(get())
    }

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
            savedRecipesRepository = get(),
        )
    }

    // ViewModel
    viewModel {
        HomeViewModel(repository = get())
    }

    viewModel {
        SearchRecipeViewModel(repository = get())
    }

    viewModel {
        SavedRecipesViewModel(
            getSavedRecipesUseCase = get(),
            bookmarkRepository = get(),
        )
    }

    viewModel {
        SplashViewModel(
            networkMonitor = get()
        )
    }
}

