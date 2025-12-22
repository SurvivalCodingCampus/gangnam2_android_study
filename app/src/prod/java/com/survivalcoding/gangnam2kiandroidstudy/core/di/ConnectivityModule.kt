package com.survivalcoding.gangnam2kiandroidstudy.core.di

import android.content.Context
import com.survivalcoding.gangnam2kiandroidstudy.core.network.ConnectivityNetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {

    @Provides
    @Singleton
    fun provideConnectivityNetworkMonitor(
        @ApplicationContext context: Context
    ): ConnectivityNetworkMonitor =
        ConnectivityNetworkMonitor(context)
}
