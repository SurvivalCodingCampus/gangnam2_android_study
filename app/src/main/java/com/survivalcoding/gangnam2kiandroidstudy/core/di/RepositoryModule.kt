package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockIngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockProfileRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProfileRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(
        recipeDataSource: RecipeDataSource,
    ): RecipeRepository {
        return RecipeRepositoryImpl(recipeDataSource)
    }

    @Provides
    @Singleton
    fun provideBookmarkRepository(): BookmarkRepository {
        return MockBookmarkRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideProfileRepository(): ProfileRepository {
        return MockProfileRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideIngredientRepository(): IngredientRepository {
        return MockIngredientRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideProcedureRepository(): ProcedureRepository {
        return MockProcedureRepositoryImpl
    }
}