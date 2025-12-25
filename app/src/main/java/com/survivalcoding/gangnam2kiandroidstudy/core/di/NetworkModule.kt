package com.survivalcoding.gangnam2kiandroidstudy.core.di

import android.content.Context
import android.net.ConnectivityManager
import com.survivalcoding.gangnam2kiandroidstudy.data.network.NetworkMonitorImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single<ConnectivityManager> {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            ?: throw IllegalStateException("ConnectivityManager not available")
    }

    single<NetworkMonitor> { NetworkMonitorImpl(get()) }

    viewModel { SplashViewModel(get()) }

}
