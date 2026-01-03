package com.misterjerry.gangnam2kiandroidstudy

import android.app.Application
import com.misterjerry.gangnam2kiandroidstudy.core.di.appModule
import com.misterjerry.gangnam2kiandroidstudy.core.di.clipboardModule
import com.misterjerry.gangnam2kiandroidstudy.core.di.dataSourceModule
import com.misterjerry.gangnam2kiandroidstudy.core.di.networkModule
import com.misterjerry.gangnam2kiandroidstudy.core.di.repositoryModule
import com.misterjerry.gangnam2kiandroidstudy.core.di.useCaseModule
import com.misterjerry.gangnam2kiandroidstudy.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RecipeAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RecipeAppApplication)
            modules(
                dataSourceModule,
                repositoryModule,
                viewModelModule,
                appModule,
                useCaseModule,
                networkModule,
                clipboardModule
            )
        }
    }
}