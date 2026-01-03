package com.misterjerry.gangnam2kiandroidstudy.core.di

import android.content.ClipboardManager
import android.content.Context
import com.misterjerry.gangnam2kiandroidstudy.data.clipboard.ClipboardRepositoryImpl
import com.misterjerry.gangnam2kiandroidstudy.domain.repository.ClipboardRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val clipboardModule = module {
    single<ClipboardManager> {
        androidContext().getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
            ?: throw IllegalStateException("ClipboardManager not available")
    }

    single<ClipboardRepository> { ClipboardRepositoryImpl(get()) }
}