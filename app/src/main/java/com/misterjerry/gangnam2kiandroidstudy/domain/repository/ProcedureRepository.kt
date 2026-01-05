package com.misterjerry.gangnam2kiandroidstudy.domain.repository

import com.misterjerry.gangnam2kiandroidstudy.domain.model.Procedure

interface ProcedureRepository {
    suspend fun getProcedureByRecipeId(id: Int): List<Procedure>
}