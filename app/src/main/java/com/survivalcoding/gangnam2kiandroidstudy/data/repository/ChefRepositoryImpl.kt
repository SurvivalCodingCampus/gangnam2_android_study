package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.ChefDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Chef
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import javax.inject.Inject

class ChefRepositoryImpl @Inject constructor(
    private val dataSource: ChefDataSource
) : ChefRepository {

    override suspend fun getChefById(id: Int): Chef? {
        return dataSource.getChefById(id)?.toModel()
    }
}
