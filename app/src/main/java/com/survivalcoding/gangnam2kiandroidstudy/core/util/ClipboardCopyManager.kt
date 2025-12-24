package com.survivalcoding.gangnam2kiandroidstudy.core.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class ClipboardCopyManager(
    context: Context,
) : CopyManager {
    private val clipboard: ClipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    override fun copy(text: String) {
        val clip: ClipData = ClipData.newPlainText("share", text)
        clipboard.setPrimaryClip(clip)
    }
}