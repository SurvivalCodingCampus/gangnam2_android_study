package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.core.isFailure
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.exception.NetworkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import okio.IOException
import kotlin.collections.emptyList

class RecipeRepositoryImpl(private val dataSource: RecipeDataSource) : RecipeRepository {
    override suspend fun findAll(): Result<List<Recipe>, NetworkError> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = dataSource.findAll()
            if (response.isFailure()) {
                return@withContext Result.Failure(NetworkError.HttpError(response.statusCode))
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
}