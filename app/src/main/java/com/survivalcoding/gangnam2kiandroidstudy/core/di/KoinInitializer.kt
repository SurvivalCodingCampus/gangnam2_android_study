package com.survivalcoding.gangnam2kiandroidstudy.core.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

fun initKoin(context: Context) {
    if (GlobalContext.getOrNull() == null) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(
                appModule,
                dataSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
                networkModule,
                databaseModule,
                flavorModule,
            )
        }
    }
}
