import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    //직렬화
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    //firebase
    id("com.google.gms.google-services")

}

android {
    namespace = "com.misterjerry.gangnam2kiandroidstudy"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.misterjerry.gangnam2kiandroidstudy"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    buildFeatures {
        compose = true
    }
    flavorDimensions += listOf("version")
    productFlavors {
        create("dev") {
            dimension = "version"
            versionNameSuffix = "-dev"
            applicationIdSuffix = ".dev"
        }
        create("prod") {
            dimension = "version"
        }
        create("qa") {
            dimension = "version"
            versionNameSuffix = "-qa"
            applicationIdSuffix = ".qa"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    //coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    //compose viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.compose.android)

    //직렬화
    implementation(libs.kotlinx.serialization.json)

    //Icon
    implementation(libs.androidx.compose.material.icons.extended)

    //runTest
    testImplementation(libs.kotlinx.coroutines.test)

    //nav3
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.material3.adaptive.navigation3)
    implementation(libs.kotlinx.serialization.core)

    //koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.insert.koin.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.compose.navigation3)

    //immutable collection
    implementation(libs.kotlinx.collections.immutable)

    //room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)
    ksp(libs.androidx.room.compiler)
    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    //firebase auth
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    //firestore
    implementation(libs.firebase.firestore)
    implementation(libs.kotlinx.coroutines.play.services)

    //Appcompat Activity
    implementation(libs.androidx.appcompat)

}
