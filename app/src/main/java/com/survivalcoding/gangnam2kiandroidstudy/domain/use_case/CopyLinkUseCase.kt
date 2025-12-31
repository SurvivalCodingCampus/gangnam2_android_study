package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipBoardRepository

class CopyLinkUseCase(
    private val clipBoardRepository: ClipBoardRepository
) {
    operator fun invoke(text: String) {
        clipBoardRepository.saveClipBoard(text)
    }
}
