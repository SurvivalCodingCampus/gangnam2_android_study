// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // firebase
    id("com.google.gms.google-services") version "4.4.4" apply false
}

// 코드래빗이 지적한 보안 취약점(CVE-2022-25647, CVE-2023-2976) 해결을 위한 강제 버전 고정
subprojects {
    configurations.all {
        resolutionStrategy {
            // GSON 취약점 해결을 위해 2.10.1로 강제 업데이트
            force("com.google.code.gson:gson:2.10.1")
            // Guava 취약점 해결을 위해 33.0.0-jre로 강제 업데이트
            force("com.google.guava:guava:33.0.0-jre")
        }
    }
}