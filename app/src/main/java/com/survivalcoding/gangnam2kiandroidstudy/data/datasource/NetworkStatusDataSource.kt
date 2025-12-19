package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.NetworkStatusDto
import kotlinx.coroutines.flow.Flow

interface NetworkStatusDataSource {
    fun observeNetworkStatus(): Flow<NetworkStatusDto>
}