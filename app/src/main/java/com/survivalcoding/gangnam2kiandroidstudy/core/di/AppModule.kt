package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.core.util.ClipboardCopyManager
import com.survivalcoding.gangnam2kiandroidstudy.core.util.CopyManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<CopyManager> { ClipboardCopyManager(androidContext()) }
}