package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FirestoreBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookmarkRepositoryModule {

    @Provides
    @Singleton
    fun provideBookmarkRepository(
        firestore: FirebaseFirestore,
        auth: FirebaseAuth
    ): BookmarkRepository {
        return FirestoreBookmarkRepositoryImpl(firestore, auth)
    }
}
