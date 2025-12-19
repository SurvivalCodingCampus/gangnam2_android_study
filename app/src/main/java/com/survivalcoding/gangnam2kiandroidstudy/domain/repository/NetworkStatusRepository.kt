package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface NetworkStatusRepository {
    suspend fun observeNetworkStatus(): Flow<NetworkStatus>
}