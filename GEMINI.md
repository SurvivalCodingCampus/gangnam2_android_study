# Gangnam2 Android Study Project

## Configuration
*   **Preferred Language:** Korean (한국어) - The agent must respond in Korean.

## Project Overview

This is a modern Android application built with Kotlin, following **Clean Architecture** principles. It utilizes the latest Jetpack Compose for UI and the experimental **Navigation 3** library for type-safe navigation.

### Architecture

The project is structured into three main layers (Clean Architecture):

*   **Presentation Layer** (`presentation/`): Contains UI components (Compose), Screens, and ViewModels.
*   **Domain Layer** (`domain/`): Contains the business logic, UseCases, and Repository interfaces. This layer is purely Kotlin and framework-agnostic.
*   **Data Layer** (`data/`): Contains the implementation of Repositories, Data Sources (API/DB), and Mappers.

### Key Technologies

*   **Language**: Kotlin
*   **UI**: Jetpack Compose (Material3)
*   **Dependency Injection**: [Koin](https://insert-koin.io/)
*   **Navigation**: [AndroidX Navigation 3](https://developer.android.com/guide/navigation) (Experimental/Alpha)
*   **Image Loading**: Coil 3
*   **Concurrency**: Kotlin Coroutines & Flow
*   **Build System**: Gradle (Kotlin DSL) with Version Catalog (`libs.versions.toml`)

## Directory Structure

```text
app/src/main/java/com/survivalcoding/gangnam2kiandroidstudy/
├── core/               # Core utilities, DI modules, and Routing
│   ├── di/             # Koin modules (App, Network, Repo, etc.)
│   └── routing/        # Navigation 3 logic (NavigationRoot.kt, Route.kt)
├── data/               # Data layer implementation
├── domain/             # Domain layer interfaces and use cases
├── presentation/       # UI layer (Screens, Components)
├── ui/                 # UI Theme, Colors, Fonts
├── AppApplication.kt   # Application class (Koin init)
└── MainActivity.kt     # Entry point
```

## Building and Running

### Prerequisites
*   JDK 11+
*   Android Studio

### Commands

*   **Build Debug APK:**
    ```bash
    ./gradlew assembleDebug
    ```

*   **Run Unit Tests:**
    ```bash
    ./gradlew test
    ```

*   **Run Instrumented Tests:**
    ```bash
    ./gradlew connectedAndroidTest
    ```

*   **Clean Build:**
    ```bash
    ./gradlew clean build
    ```

## Development Conventions

*   **Navigation:** All navigation logic is centralized in `core/routing/NavigationRoot.kt` using `NavDisplay` and `entryProvider`. Routes are defined in `core/routing/Route.kt`.
*   **Dependency Injection:** Modules are defined in `core/di/`. Ensure new dependencies are added to the appropriate module and the module is added to the `startKoin` block in `AppApplication.kt`.
*   **Composables:** Screen-level composables usually have a `Root` suffix (e.g., `HomeRoot`, `SavedRecipesRoot`) handling state and events, calling into pure UI composables.