package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.ChefDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef

fun ChefDto.toModel(): Chef {
    return Chef(
        id = this.id ?: -1,
        name = this.name ?: "Unknown",
        imageUrls = this.image ?: "",
        address = this.address ?: ""
    )
}
