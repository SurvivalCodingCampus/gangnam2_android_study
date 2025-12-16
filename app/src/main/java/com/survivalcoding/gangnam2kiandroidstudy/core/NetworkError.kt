package com.survivalcoding.gangnam2kiandroidstudy.core

sealed class NetworkError : Exception() {
    object NetworkUnavailable : NetworkError()
    object Timeout : NetworkError()
    object ParseError : NetworkError()
    data class HttpError(val code: Int) : NetworkError()
    data class Unknown(override val message: String): NetworkError()
}
