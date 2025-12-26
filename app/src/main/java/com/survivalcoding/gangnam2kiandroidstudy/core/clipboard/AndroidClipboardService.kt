package com.survivalcoding.gangnam2kiandroidstudy.core.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import com.survivalcoding.gangnam2kiandroidstudy.domain.service.ClipboardService

class AndroidClipboardService(
    private val clipboardManager: ClipboardManager
) : ClipboardService {
    override fun copy(label: String, text: String): Boolean {
        return try {
            val clip = ClipData.newPlainText(label, text)

            clipboardManager.setPrimaryClip(clip)
            clipboardManager.hasPrimaryClip() && clipboardManager.primaryClip?.getItemAt(0)?.text == text
        } catch (e: Exception) {
            false
        }
    }
}