package com.survivalcoding.gangnam2kiandroidstudy.di

import android.content.Context
import android.net.ConnectivityManager
import com.survivalcoding.gangnam2kiandroidstudy.data.recipe.network.NetworkMonitorImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkMonitor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {

    single {
        androidContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    single<NetworkMonitor> {
        NetworkMonitorImpl(get())
    }
}
