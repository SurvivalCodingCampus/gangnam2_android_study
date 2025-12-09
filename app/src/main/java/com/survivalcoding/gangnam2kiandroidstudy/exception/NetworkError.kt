package com.survivalcoding.gangnam2kiandroidstudy.exception

sealed class NetworkError {
    object NetworkUnavailable : NetworkError()
    object Timeout : NetworkError()
    object ParseError : NetworkError()
    data class HttpError(val code: Int) : NetworkError()
    data class Unknown(val message: String): NetworkError()
}