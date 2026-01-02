package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.NetworkStatusDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockNetworkStatusDataSourceImpl: NetworkStatusDataSource {
    override fun observeNetworkStatus(): Flow<NetworkStatusDto> {
        return flowOf(
            NetworkStatusDto(isConnected = true)
        )
    }
}