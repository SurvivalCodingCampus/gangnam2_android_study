package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ClipboardRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.UserRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.UserRepository
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val recipeRepositoryModule = module {
    single<RecipeRepository> { RecipeRepositoryImpl(get(), get()) }
}

val ingredientRepositoryModule = module {
    single<IngredientRepository> { IngredientRepositoryImpl(get()) }
}

val procedureRepositoryModule = module {
    single<ProcedureRepository> { ProcedureRepositoryImpl(get()) }
}

val clipboardRepositoryModule = module {
    single<ClipboardRepository> { ClipboardRepositoryImpl(androidContext()) }
}

val userRepositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}

// Mock Auth and Bookmark for dev flavor if not provided
val authRepositoryModule = module {
    single<AuthRepository> {
        object : AuthRepository {
            override val currentUser: Flow<com.survivalcoding.gangnam2kiandroidstudy.domain.model.User?> = flowOf(null)
            override suspend fun signOut() {}
            override suspend fun signInWithGoogle(idToken: String): Result<Unit, NetworkError> = Result.Success(Unit)
        }
    }
}

val bookmarkRepositoryModule = module {
    single<BookmarkRepository> {
        object : BookmarkRepository {
            override fun getBookmarkedRecipeIds(): Flow<List<Int>> = flowOf(emptyList())
            override suspend fun toggleBookmark(recipeId: Int) {}
        }
    }
}

val repositoryModule = listOf(
    recipeRepositoryModule,
    ingredientRepositoryModule,
    procedureRepositoryModule,
    clipboardRepositoryModule,
    userRepositoryModule,
    authRepositoryModule,
    bookmarkRepositoryModule
)
