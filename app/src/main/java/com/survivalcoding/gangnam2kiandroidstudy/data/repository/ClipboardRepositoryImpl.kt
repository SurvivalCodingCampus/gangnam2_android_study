package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository

class ClipboardRepositoryImpl(private val context: Context) : ClipboardRepository {
    override fun copyText(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Recipe Link", text)
        clipboard.setPrimaryClip(clip)
    }
}