package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.NetworkStatusDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NetworkStatus
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.NetworkStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NetworkStatusRepositoryImpl(
    private val networkStatusDataSource: NetworkStatusDataSource
) : NetworkStatusRepository {
    override suspend fun observeNetworkStatus(): Flow<NetworkStatus> {
        return networkStatusDataSource.observeNetworkStatus().map {
            it.toModel()
        }
    }
}