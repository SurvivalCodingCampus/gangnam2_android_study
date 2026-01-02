package com.misterjerry.gangnam2kiandroidstudy.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Procedures(
    val procedures: List<Procedure>
)