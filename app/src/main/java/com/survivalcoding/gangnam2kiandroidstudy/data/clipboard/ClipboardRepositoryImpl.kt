package com.survivalcoding.gangnam2kiandroidstudy.data.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository

class ClipboardRepositoryImpl(
    private val clipboardManager: ClipboardManager
) : ClipboardRepository {
    override fun copyText(text: String) {
        val clip = ClipData.newPlainText("Recipe Link", text)
        clipboardManager.setPrimaryClip(clip)
    }
}