package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.domain.service.ClipboardService

class CopyLinkUseCase(
    private val clipboardService: ClipboardService
) {
    operator fun invoke(link: String): Boolean {
        return clipboardService.copy("recipe_link", link)
    }
}