package com.survivalcoding.gangnam2kiandroidstudy.domain.model

sealed interface NetworkStatus {
    object Connected: NetworkStatus
    object Disconnected: NetworkStatus
}