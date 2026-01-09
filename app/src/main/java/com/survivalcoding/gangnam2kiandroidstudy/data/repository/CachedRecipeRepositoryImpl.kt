package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.local.RecipeDao
import com.survivalcoding.gangnam2kiandroidstudy.data.local.toDTO
import com.survivalcoding.gangnam2kiandroidstudy.data.local.toEntity
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.first
import kotlin.coroutines.cancellation.CancellationException

class CachedRecipeRepositoryImpl(
    private val dataSource: RecipeDataSource,
    private val recipeDao: RecipeDao
) : RecipeRepository {

    override suspend fun findRecipe(id: Long): Result<Recipe, String> {
        return try {
            // 1. 로컬 캐시 먼저 확인
            val localRecipe = recipeDao.getRecipeById(id)
            if (localRecipe != null) {
                return Result.Success(localRecipe.toDTO().toModel())
            }

            // 2. 캐시 없으면 원격 호출
            val remoteRecipe = dataSource.getRecipe(id)
            if (remoteRecipe != null) {
                // 원격 성공 시 캐시 저장
                remoteRecipe.toEntity()?.let { recipeDao.insertRecipe(it) }
                Result.Success(remoteRecipe.toModel())
            } else {
                Result.Error("Recipe not found: $id")
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.Error("error : findRecipe($id) 실패 - ${e.message}")
        }
    }

    override suspend fun findRecipes(): Result<List<Recipe>, String> {
        return try {
            // 1. 우선 로컬 캐시 데이터 반환 (Offline-First)
            val localRecipes = recipeDao.getAllRecipes().first()
            
            // 2. 백그라운드 느낌으로 원격 업데이트 (여기서는 순차 실행)
            val remoteRecipes = dataSource.getRecipes()?.filterNotNull()
            if (remoteRecipes != null) {
                recipeDao.insertRecipes(remoteRecipes.mapNotNull { it.toEntity() })
                // 원격 데이터가 있으면 최신 정보로 반환
                Result.Success(remoteRecipes.map { it.toModel() })
            } else if (localRecipes.isNotEmpty()) {
                // 원격 실패 시 로컬 데이터라도 반환
                Result.Success(localRecipes.map { it.toDTO().toModel() })
            } else {
                Result.Error("Recipe list is empty")
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.Error("error : findRecipes() 실패 - ${e.message}")
        }
    }
}
