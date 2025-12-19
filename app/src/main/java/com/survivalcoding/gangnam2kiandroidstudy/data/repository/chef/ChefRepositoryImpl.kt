package com.survivalcoding.gangnam2kiandroidstudy.data.repository.chef

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.chef.ChefDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.chef.Chef
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChefRepositoryImpl @Inject constructor(
    private val chefDataSource: ChefDataSource
) : ChefRepository {

    override suspend fun getChefs(): List<Chef> = withContext(Dispatchers.IO) {
        chefDataSource.getChefs()
    }
}
