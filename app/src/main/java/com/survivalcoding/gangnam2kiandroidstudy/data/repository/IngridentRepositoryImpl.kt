package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.AssetDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingrident
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngridentRepository

class IngridentRepositoryImpl(
    private val dataSource: AssetDataSource
) : IngridentRepository {

    override suspend fun getIngridents(id: Int): List<Ingrident> {
        return dataSource.getIngridents().ingredients
            ?.filter { it.recipeId == id }
            ?.map { it.toModel() }
            ?: emptyList()
    }
}