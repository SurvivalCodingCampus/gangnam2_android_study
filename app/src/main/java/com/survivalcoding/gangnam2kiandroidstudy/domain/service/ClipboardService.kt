package com.survivalcoding.gangnam2kiandroidstudy.domain.service

interface ClipboardService {
    fun copy(label: String, text: String): Boolean
}