package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.UserDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngridentRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngridentRepository
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
    fun provideIngridentRepository(recipeDataSource: RecipeDataSource): IngridentRepository {
        return IngridentRepositoryImpl(recipeDataSource)
    }

    @Provides
    fun provideProcedureRepository(procedureDataSource: ProcedureDataSource): ProcedureRepository {
        return ProcedureRepositoryImpl(procedureDataSource)
    }

    @Provides
    fun provideBookmarkRepository(userDataSource: UserDataSource): BookmarkRepository {
        return BookmarkRepositoryImpl(userDataSource)
    }
}