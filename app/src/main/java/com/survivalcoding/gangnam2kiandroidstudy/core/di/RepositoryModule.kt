package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.BuildConfig
import com.survivalcoding.gangnam2kiandroidstudy.data.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.ChefDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.IngredientDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.ProcedureDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeIngredientDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ChefRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FirebaseBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FirebaseSignUpRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SignUpRepository
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
    fun provideSignUpRepository(
        auth: FirebaseAuth
    ): SignUpRepository {
        return FirebaseSignUpRepositoryImpl(auth)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(
        recipeDataSource: RecipeDataSource,
    ): RecipeRepository {
        return RecipeRepositoryImpl(recipeDataSource)
    }

    @Provides
    @Singleton
    fun provideBookmarkRepository(
        bookmarkDao: BookmarkDao,
        firestore: FirebaseFirestore,
        auth: FirebaseAuth
    ): BookmarkRepository {
        return if (BuildConfig.FLAVOR == "prod" || BuildConfig.FLAVOR == "qa") {
            FirebaseBookmarkRepositoryImpl(firestore, auth, bookmarkDao)
        } else {
            BookmarkRepositoryImpl(bookmarkDao)
        }
    }

    @Provides
    @Singleton
    fun provideChefRepository(
        chefDataSource: ChefDataSource
    ): ChefRepository {
        return ChefRepositoryImpl(chefDataSource)
    }


    @Provides
    @Singleton
    fun provideIngredientRepository(
        ingredientDataSource: IngredientDataSource,
        recipeIngredientDataSource: RecipeIngredientDataSource
    ): IngredientRepository {
        return IngredientRepositoryImpl(
            ingredientDataSource,
            recipeIngredientDataSource
        )
    }

    @Provides
    @Singleton
    fun provideProcedureRepository(
        procedureDataSource: ProcedureDataSource
    ): ProcedureRepository {
        return ProcedureRepositoryImpl(procedureDataSource)
    }

}
