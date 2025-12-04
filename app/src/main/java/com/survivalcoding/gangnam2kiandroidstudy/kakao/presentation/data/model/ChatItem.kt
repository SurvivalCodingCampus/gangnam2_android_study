package com.survivalcoding.gangnam2kiandroidstudy.kakao.presentation.data.model

data class ChatItem(
    val imageUrl: String,
    val name: String,
    val message: String,
    val time: String,
    val isRead: Boolean,
)