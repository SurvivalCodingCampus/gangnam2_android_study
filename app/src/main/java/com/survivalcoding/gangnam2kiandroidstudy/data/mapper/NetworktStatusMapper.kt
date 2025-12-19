package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.NetworkStatusDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NetworkStatus

fun NetworkStatusDto.toModel(): NetworkStatus {
    return if (isConnected)
        NetworkStatus.Connected
    else
        NetworkStatus.Disconnected
}