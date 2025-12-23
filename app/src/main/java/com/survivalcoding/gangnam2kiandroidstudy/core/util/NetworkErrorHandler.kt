package com.survivalcoding.gangnam2kiandroidstudy.core.util

import android.util.Log
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.HttpException
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.serialization.SerializationException
import java.io.IOException

object NetworkErrorHandler {

    suspend fun <T> handle(block: suspend () -> AppResult<T, NetworkError>): AppResult<T, NetworkError> =
        try {
            block()
        } catch (e: HttpException) {
            Log.e("NetworkErrorHandler", e.stackTraceToString())
            AppResult.Error(NetworkError.HttpError(e.code))
        } catch (e: IOException) {
            Log.e("NetworkErrorHandler", e.stackTraceToString())
            AppResult.Error(NetworkError.NetworkUnavailable)
        } catch (e: TimeoutCancellationException) {
            Log.e("NetworkErrorHandler", e.stackTraceToString())
            AppResult.Error(NetworkError.Timeout)
        } catch (e: SerializationException) {
            Log.e("NetworkErrorHandler", e.stackTraceToString())
            AppResult.Error(NetworkError.ParseError)
        } catch (e: Exception) {
            Log.e("NetworkErrorHandler", e.stackTraceToString())
            AppResult.Error(NetworkError.Unknown("에러가 발생했습니다"))
        }
}