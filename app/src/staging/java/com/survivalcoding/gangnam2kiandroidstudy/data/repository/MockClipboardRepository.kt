package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository

class MockClipboardRepository : ClipboardRepository {
    override fun copyToClipboard(text: String, label: String) {
        // Mock implementation
    }
}
