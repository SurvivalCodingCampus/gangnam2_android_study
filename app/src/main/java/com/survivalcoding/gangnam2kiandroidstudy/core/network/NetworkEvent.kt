package com.survivalcoding.gangnam2kiandroidstudy.core.network

sealed interface NetworkEvent {
    data object Connected : NetworkEvent
    data object Disconnected : NetworkEvent
}