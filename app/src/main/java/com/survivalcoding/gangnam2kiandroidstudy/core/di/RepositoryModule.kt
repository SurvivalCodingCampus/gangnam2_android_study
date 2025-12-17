package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRecipeRepository(recipeDataSource: RecipeDataSource): RecipeRepository {
        return RecipeRepositoryImpl(recipeDataSource)
    }

    @Provides
    fun provideBookmarkRepository(recipeDataSource: RecipeDataSource): BookmarkRepository {
        return BookmarkRepositoryImpl(recipeDataSource)
    }

    @Provides
    fun provideIngredientRepository(recipeDataSource: RecipeDataSource): IngredientRepository {
        return IngredientRepositoryImpl(recipeDataSource)
    }

    @Provides
    fun provideProcedureRepository(): ProcedureRepository {
        return ProcedureRepositoryImpl()
    }
}