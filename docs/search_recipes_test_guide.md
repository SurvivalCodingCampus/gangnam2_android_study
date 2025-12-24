# SearchRecipesScreen 통합 테스트 가이드

## 1. 개요
이 문서는 `SearchRecipesScreen`의 UI 요소와 사용자 상호작용을 검증하기 위해 작성된 통합 테스트(Instrumented Test)에 대한 설명을 담고 있습니다.

## 2. 테스트 파일 위치
*   **경로**: `app/src/androidTest/java/com/survivalcoding/gangnam2kiandroidstudy/presentation/searchrecipes/SearchRecipesScreenTest.kt`

## 3. 테스트 시나리오
작성된 테스트(`SearchRecipesScreenTest`)는 다음 4가지 주요 시나리오를 검증합니다.

### 1) 레시피 목록 표시 (`displayRecipes`)
*   **목표**: 화면 진입 시 레시피 데이터가 올바르게 렌더링되는지 확인합니다.
*   **검증 항목**:
    *   레시피 이름이 화면에 표시되는지 확인
    *   셰프 이름이 표시되는지 확인

### 2) 검색어 입력 동작 (`triggerSearchAction`)
*   **목표**: 검색창에 텍스트를 입력했을 때, 해당 액션(`OnSearchQueryChange`)이 올바르게 발생하는지 확인합니다.
*   **검증 항목**:
    *   검색창(`SearchField`)에 "Pasta" 입력 시도
    *   `lastAction`이 `OnSearchQueryChange("Pasta")`로 업데이트되는지 검증

### 3) 필터 시트 열기 (`triggerFilterSheet`)
*   **목표**: 설정(필터) 아이콘 클릭 시 필터 옵션을 담은 바텀 시트가 나타나는지 확인합니다.
*   **검증 항목**:
    *   설정 아이콘(`setting icon`) 클릭
    *   "Filter Search" 텍스트를 포함한 UI 요소(바텀 시트)가 화면에 표시되는지 검증

### 4) 뒤로 가기 동작 (`triggerBackAction`)
*   **목표**: 상단 뒤로 가기 버튼 클릭 시 이전 화면으로 이동하는 액션(`OnBackClick`)이 발생하는지 확인합니다.
*   **검증 항목**:
    *   뒤로 가기 아이콘 클릭
    *   `lastAction`이 `OnBackClick`인지 검증

## 4. 테스트 실행 방법

이 테스트는 Android 기기나 에뮬레이터가 연결된 상태에서 실행해야 합니다.

### 터미널에서 실행
프로젝트 루트 디렉토리에서 다음 명령어를 실행합니다:

```bash
./gradlew connectedAndroidTest "-Pandroid.testInstrumentationRunnerArguments.class=com.survivalcoding.gangnam2kiandroidstudy.presentation.searchrecipes.SearchRecipesScreenTest"
```

### 주의 사항
*   **연결된 기기 필요**: `connectedAndroidTest` 태스크는 실제 기기나 에뮬레이터가 없으면 `No connected devices!` 에러와 함께 실패합니다. 테스트 실행 전 에뮬레이터를 구동해 주세요.
*   **Gradle 캐시**: 빌드 관련 문제가 발생할 경우 `./gradlew clean` 후 다시 시도해 보세요.
