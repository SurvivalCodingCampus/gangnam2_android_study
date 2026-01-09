package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.core.di.initKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(this)
    }
}