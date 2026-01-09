package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.core.coroutine.ApplicationScope
import com.survivalcoding.gangnam2kiandroidstudy.data.network.NetworkMonitorImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkMonitor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<NetworkMonitor> { NetworkMonitorImpl(get(), get<ApplicationScope>()) }

    single {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }, contentType = ContentType.Any) // 모든 ContentType에 대해 JSON 파싱 시도 (또는 Text.Plain 지정)
            }
        }
    }
}
