package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.core.util.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.util.OnlineNetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FirebaseBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import org.koin.dsl.module

val versionModule = module {
    single<NetworkMonitor> { OnlineNetworkMonitor }
    single<BookmarkRepository> { FirebaseBookmarkRepositoryImpl() }
}