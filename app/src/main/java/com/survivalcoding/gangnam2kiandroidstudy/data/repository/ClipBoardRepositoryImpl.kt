package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipBoardRepository

class ClipboardRepositoryImpl(context: Context) : ClipBoardRepository {
    private val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    override fun saveClipBoard(text: String) {
        clipboard.setPrimaryClip(ClipData.newPlainText("copy text",text))
    }

}