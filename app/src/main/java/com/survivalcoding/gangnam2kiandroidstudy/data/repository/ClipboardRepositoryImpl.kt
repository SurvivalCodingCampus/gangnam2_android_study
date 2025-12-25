package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ClipboardRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ClipboardRepository {
    override fun copyText(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Link", text)
        clipboard.setPrimaryClip(clip)
    }
}
