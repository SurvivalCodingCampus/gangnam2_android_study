package com.survivalcoding.gangnam2kiandroidstudy.core.di

import android.content.ClipboardManager
import android.content.Context
import com.survivalcoding.gangnam2kiandroidstudy.core.clipboard.AndroidClipboardService
import com.survivalcoding.gangnam2kiandroidstudy.domain.service.ClipboardService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val clipboardModule = module {
    single<ClipboardService> {
        AndroidClipboardService(
            clipboardManager = androidContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        )
    }
}