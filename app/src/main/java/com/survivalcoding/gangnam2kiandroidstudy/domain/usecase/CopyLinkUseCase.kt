package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipBoardRepository

class CopyLinkUseCase(
    private val clipBoardRepository: ClipBoardRepository
) {
    fun execute(text: String) {
        clipBoardRepository.saveClipBoard(text)
    }
}