package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.util.Log
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.core.isFailure
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.exception.NetworkError
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.serialization.SerializationException
import okio.IOException

class RecipeRepositoryImpl(private val dataSource: RecipeDataSource) : RecipeRepository {
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

            Log.d("RecipeRepositoryImpl search: ", "$query : $time : $rate : $category")

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
}