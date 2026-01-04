package com.survivalcoding.gangnam2kiandroidstudy.core.di

import MockBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import org.koin.dsl.module

val flavorModule = module {
    single<BookmarkRepository> { MockBookmarkRepositoryImpl() }
}
