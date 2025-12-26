package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository

class CopyLinkUseCase(
    private val repository: ClipboardRepository
) {
    fun execute(link: String) {
        repository.copyText(link)
    }
}