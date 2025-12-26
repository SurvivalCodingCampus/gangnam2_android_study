package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

// Hilt 기반 Fake 주입에 필요
// AppApplication 을 Hilt가 테스트 전용으로 제공하는 Application 으로 교체
class CustomTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        android.util.Log.d("CustomTestRunner", "Using HiltTestApplication")
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
