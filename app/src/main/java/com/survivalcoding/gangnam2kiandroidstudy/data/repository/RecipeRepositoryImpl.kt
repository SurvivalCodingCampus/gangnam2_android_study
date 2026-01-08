package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.database.Cursor
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.core.isFailure
import com.survivalcoding.gangnam2kiandroidstudy.data.dao.RecipeDao
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.data.model.entity.RecipeEntity
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerializationException
import okio.IOException

class RecipeRepositoryImpl(
    private val dataSource: RecipeDataSource,
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun findAll(): Result<List<Recipe>, NetworkError> {
        return try {
            val response = dataSource.findAll()
            if (response.isFailure()) {
                return Result.Failure(NetworkError.HttpError(response.statusCode))
            }

            val recipes = response.body?.recipes ?: emptyList()

            recipes.filter { it.id != null }
                .map { it.toModel() }
                .let { Result.Success(it) }
        } catch (_: IOException) {
            Result.Failure(NetworkError.NetworkUnavailable)
        } catch (_: TimeoutCancellationException) {
            Result.Failure(NetworkError.Timeout)
        } catch (_: SerializationException) {
            Result.Failure(NetworkError.ParseError)
        } catch (e: Exception) {
            Result.Failure(NetworkError.Unknown(e.message ?: "오류가 발생했습니다."))
        }
    }

    override suspend fun search(
        query: String,
        time: String,
        rate: Double,
        category: String,
    ): Result<List<Recipe>, NetworkError> {
        return try {
            val response = dataSource.findAll()
            if (response.isFailure()) {
                return Result.Failure(NetworkError.HttpError(response.statusCode))
            }

            val recipes = response.body?.recipes ?: emptyList()

            recipes.filter { recipe ->
                val queryMatch = if (query.isBlank()) {
                    true
                } else {
                    recipe.name?.contains(query, ignoreCase = true) ?: false
                }

                val categoryMatch = if (category == "All") {
                    true
                } else {
                    recipe.category.equals(category, ignoreCase = true)
                }

                val rateMatch = recipe.rating?.let { (it >= rate) } ?: false

                // TODO : Filter Time은 값이 없어서 보류

                queryMatch && categoryMatch && rateMatch
            }
                .map { it.toModel() }
                .let { Result.Success(it) }
        } catch (_: IOException) {
            Result.Failure(NetworkError.NetworkUnavailable)
        } catch (_: TimeoutCancellationException) {
            Result.Failure(NetworkError.Timeout)
        } catch (_: SerializationException) {
            Result.Failure(NetworkError.ParseError)
        } catch (e: Exception) {
            Result.Failure(NetworkError.Unknown(e.message ?: "오류가 발생했습니다."))
        }
    }

    override suspend fun getRecipes(): List<Recipe> {
        return try {
            val response = dataSource.findAll()
            if (response.isFailure()) {
                throw NetworkError.HttpError(response.statusCode)
            }

            response.body?.recipes
                ?.filter { it.id != null }
                ?.map { it.toModel() }
                ?: emptyList()
        } catch (_: IOException) {
            throw NetworkError.NetworkUnavailable
        } catch (_: TimeoutCancellationException) {
            throw NetworkError.Timeout
        } catch (_: SerializationException) {
            throw NetworkError.ParseError
        } catch (e: Exception) {
            throw NetworkError.Unknown(e.message ?: "오류가 발생했습니다.")
        }
    }

    override suspend fun getRecipeById(recipeId: Int): Recipe? {
        return try {
            val response = dataSource.findAll()
            if (response.isFailure()) {
                throw NetworkError.HttpError(response.statusCode)
            }

            response.body?.recipes
                ?.firstOrNull() { it.id == recipeId }
                    ?.toModel()
        } catch (_: IOException) {
            throw NetworkError.NetworkUnavailable
        } catch (_: TimeoutCancellationException) {
            throw NetworkError.Timeout
        } catch (_: SerializationException) {
            throw NetworkError.ParseError
        } catch (e: Exception) {
            throw NetworkError.Unknown(e.message ?: "오류가 발생했습니다.")
        }
    }

    override fun getRecipesCursor(): Cursor {
        return try {
            val cursor = recipeDao.getAllCursor()
            if (cursor.count == 0) {
                android.util.Log.d("RecipeRepositoryImpl", "getRecipesCursor: DB empty, syncing...")
                runBlocking {
                    syncDatabase()
                }
                recipeDao.getAllCursor()
            } else {
                android.util.Log.d("RecipeRepositoryImpl", "getRecipesCursor: returning cursor with ${cursor.count} items")
                cursor
            }
        } catch (e: Exception) {
            android.util.Log.e("RecipeRepositoryImpl", "getRecipesCursor: error", e)
            throw e
        }
    }

    override fun getRecipeCursorById(recipeId: Int): Cursor {
        return recipeDao.getByIdCursor(recipeId)
    }

    private suspend fun syncDatabase() {
        try {
            val recipes = getRecipes()
            android.util.Log.d("RecipeRepositoryImpl", "syncDatabase: fetched ${recipes.size} recipes from network")
            recipeDao.insertAll(recipes.map { it.toEntity() })
        } catch (e: Exception) {
            android.util.Log.e("RecipeRepositoryImpl", "syncDatabase: failed to sync", e)
        }
    }

    private fun Recipe.toEntity() = RecipeEntity(
        id = id,
        category = category,
        name = name,
        image = image,
        chef = chef,
        time = time,
        rating = rating
    )
}