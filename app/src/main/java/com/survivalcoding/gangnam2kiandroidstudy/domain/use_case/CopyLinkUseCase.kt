package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository
import javax.inject.Inject

class CopyLinkUseCase @Inject constructor(
    private val clipboardRepository: ClipboardRepository
) {
    fun execute(link: String) {
        clipboardRepository.copyText(link)
    }
}
