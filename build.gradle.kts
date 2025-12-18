// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // 안정성이 검증된 버전으로 직접 명시합니다.
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0" apply false
}
