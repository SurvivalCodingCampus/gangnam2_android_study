package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.DataRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.DataRepositoryImpl

class AppApplication: Application() {

    val dataRepository: DataRepository by lazy {
        DataRepositoryImpl()
    }

    override fun onCreate() {
        super.onCreate()

        println("AppApplication created")
    }
}