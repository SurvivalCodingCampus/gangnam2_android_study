# Gangnam2kiAndroidStudy - Project Context

## 1. Project Overview
This project is an Android application designed for study and practice, focusing on modern Android development practices. It implements **Clean Architecture** and utilizes **Jetpack Compose** for the UI.

*   **Language**: Kotlin
*   **Minimum SDK**: 24
*   **Target SDK**: 36
*   **Compile SDK**: 36
*   **Build System**: Gradle (Kotlin DSL)

## 2. Key Technologies & Libraries

### UI & Presentation
*   **Jetpack Compose**: Main UI toolkit (Material3).
*   **Navigation**: `androidx.navigation:navigation-compose` & `androidx.navigation3` (experimental/beta).
*   **Image Loading**: Coil 3.

### Architecture & Dependency Injection
*   **Clean Architecture**: Strict separation of concerns (Data, Domain, Presentation layers).
*   **DI Framework**: **Koin** (replacing Hilt/Dagger conventions typically found in similar projects).
*   **State Management**: MVVM / MVI patterns using `ViewModel` and `StateFlow`.

### Data & Network
*   **Serialization**: `kotlinx-serialization`, `Gson`.
*   **Async Operations**: Kotlin Coroutines & Flow.

## 3. Architecture & Directory Structure
The project follows a **Clean Architecture** structure as defined in `docs/refactoring_guide.md`.

```
app/src/main/java/com/survivalcoding/gangnam2kiandroidstudy/
├── data/                 # Data Layer (Repositories impl, DataSources, DTOs)
│   ├── repository/       # Repository Implementations
│   ├── datasource/       # Remote/Local Data Sources
│   └── dto/              # Data Transfer Objects
├── domain/               # Domain Layer (Business Logic)
│   ├── model/            # Pure Domain Models
│   ├── repository/       # Repository Interfaces
│   └── usecase/          # Use Cases (Interactors)
└── presentation/         # Presentation Layer (UI)
    ├── ui/               # Theme, Colors, Fonts
    ├── component/        # Reusable UI Components
    ├── [feature]/        # Feature-specific packages (e.g., signin, signup)
    │   ├── [Feature]Screen.kt
    │   ├── [Feature]ViewModel.kt
    │   └── [Feature]State.kt
    └── MainActivity.kt
```

## 4. Build & Run
The project uses Gradle with multiple product flavors.

*   **Build Debug**: `./gradlew assembleDevDebug` or `./gradlew assembleDebug`
*   **Run Unit Tests**: `./gradlew test`
*   **Run Instrumented Tests**: `./gradlew connectedAndroidTest`

### Product Flavors
*   `dev`: Development environment (suffix: `.dev`)
*   `qa`: QA environment (suffix: `.qa`)
*   `prod`: Production environment

## 5. Development Conventions
*   **Refactoring**: Follow the guide in `docs/refactoring_guide.md` when restructuring or adding new features.
*   **Naming**:
    *   ViewModels: `[Feature]ViewModel`
    *   Screens: `[Feature]Screen`
    *   States: `[Feature]State`
*   **Dependency Injection**: Use Koin modules for providing dependencies.
*   **Navigation**: Implementing Navigation3 (beta) alongside standard Navigation Compose.

## 6. Learning Resources (TIL)
The `TIL/` directory contains daily learning notes covering topics like:
*   MVVM, MVI, EventDriven patterns.
*   Clean Architecture & DI.
*   UI State management.
