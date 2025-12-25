package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository

class CopyLinkUseCase(private val clipboardRepository: ClipboardRepository) {
    operator fun invoke(text: String) {
        clipboardRepository.copyText(text)
    }
}