# 테스트 코드 생성 전략 (Strategy)

이 문서는 프로젝트의 아키텍처(Clean Architecture + MVVM + Koin)를 기반으로, Jetpack Compose를 사용한 효과적인 안드로이드 UI 및 통합 테스트를 생성하기 위한 프롬프트 설계 전략을 정리합니다.

## 1. 프롬프트 작성을 위한 사전 준비
AI에게 테스트 코드 생성을 요청하기 전에, 대상 컴포넌트가 "테스트 가능한 상태(Test-Ready)"인지 확인해야 합니다.

### **A. 접근성 및 식별자 (Identifiers)**
UI 요소와 상호작용하려면 AI가 해당 요소를 찾을 수 있는 방법이 필요합니다.
*   **전략:** 테스트 코드를 생성하기 **전에**, 소스 코드에 `testTag` 또는 고유한 `contentDescription`을 추가하도록 요청하세요.
*   **프롬프트 예시:**
    > "`CustomSearchField.kt`의 `BasicTextField`에 `Modifier.testTag("SearchField")`를 추가해줘. 나중에 테스트에서 타겟팅할 수 있게."

### **B. 모의 데이터(Mock Data) 준비**
테스트는 결정론적인 데이터를 필요로 합니다.
*   **전략:** `Preview`에서 사용되는 파라미터나 `DataSourceImpl`에 정의된 모의 데이터 클래스를 AI가 참조할 수 있도록 프롬프트에 명시하거나 파일 경로를 제공하세요.

---

## 2. 프롬프트 구조 템플릿
컴파일 가능한 고품질의 테스트 코드를 얻으려면 다음 구조를 활용하세요.

### **템플릿**
```markdown
**Context (문맥):**
- 대상 화면/컴포넌트: [파일 경로]
- ViewModel/State: [파일 경로]
- 네비게이션 로직 (해당 시): [파일 경로]

**Goal (목표):**
[기능 이름]에 대한 [계기 테스트(Instrumented)/단위 테스트(Unit)]를 작성해줘.

**Test Scenarios (테스트 시나리오):**
1. [시나리오 1]: [초기 상태]일 때, [동작]을 수행하면, [예상 결과]가 나타나야 함.
2. [시나리오 2]: ...

**Technical Constraints (기술적 제약):**
- `createComposeRule`을 사용할 것.
- `onNodeWithText` 또는 `onNodeWithTag`를 사용할 것.
- (네비게이션의 경우) 간소화된 `TestNavigationRoot` 또는 독립적인 `NavHost` 설정을 사용할 것.
- 프로젝트 컨벤션(JUnit4, Koin)을 따를 것.
```

---

## 3. 시나리오별 테스트 전략

### **시나리오 A: UI 상태 검증 (Visibility)**
상태 변화에 따라 요소가 화면에 나타나거나 사라지는지 확인 (예: 빈 리스트 vs 데이터가 있는 리스트).

*   **주요 프롬프트 지시:**
    > "리스트가 비어있을 때 '저장된 레시피가 없습니다'라는 텍스트가 표시되는지 확인하고, 데이터가 있을 때는 '레시피 이름'이 표시되는지 검증해줘."
*   **검증 패턴:** `assertIsDisplayed()` / `assertIsNotDisplayed()`

### **시나리오 B: 사용자 상호작용 (Input & Click)**
텍스트 입력 필드에 입력하거나 버튼을 클릭하는 동작 테스트.

*   **주요 프롬프트 지시:**
    > "'SearchField' 태그를 가진 노드에 'ribs'를 입력하고, 리스트가 'ribs'를 포함하는 항목만 보여주도록 필터링되는지 검증해줘."
*   **동작 패턴:** `performTextInput("...")`, `performClick()`

### **시나리오 C: 통합 및 네비게이션 (Integration & Navigation)**
한 화면에서 다른 화면으로의 전환 테스트. *네비게이션 스택의 격리가 필요합니다.*

*   **주요 프롬프트 지시:**
    > "[A 화면]에서 시작하는 임시 `TestNavigationRoot`를 만드는 테스트를 작성해줘. [항목]을 클릭했을 때, 목적지 화면에만 있는 고유한 텍스트를 확인하여 [B 화면]으로 이동했는지 검증해줘."
*   **이유:** 전체 앱 흐름(Splash -> Login -> Home)을 모두 거치는 테스트는 깨지기 쉽고 느립니다.
*   **구현 패턴:**
    ```kotlin
    @Composable
    fun TestSearchNavigationRoot() {
        val navController = rememberNavBackStack(Route.시작화면)
        NavDisplay(...) { entry<Route.시작화면> { ... } }
    }
    ```

---

## 4. 흔한 문제 및 해결 방법

| 문제점 | 해결 전략 |
| :--- | :--- |
| **"요소를 찾을 수 없음"** | 소스 코드에 `testTag`를 먼저 추가하도록 요청하거나 `contentDescription`을 확인하세요. |
| **중복된 매칭 (Ambiguous)** | 동일한 텍스트가 여러 개라면 `onAllNodesWithText(...).onFirst()`를 쓰거나 부모/자식 관계를 명시하도록 지시하세요. |
| **네비게이션 크래시** | 실제 `NavigationRoot`에 의존하지 마세요. 테스트 파일 내에 최소한의 독립적인 `NavHost/NavDisplay` 래퍼를 만들라고 요청하세요. |
| **Koin 의존성 누락** | ViewModel이 의존성을 주입받는 경우, 테스트 규칙에서 모의(Mock) 모듈을 제공하거나 실제 모듈을 로드하도록 명시하세요. |

## 5. 예시 프롬프트 (바로 복사해서 사용 가능)
*"SearchRecipesScreen에 대한 통합 테스트가 필요해. 관련 파일은 `SearchRecipesScreen.kt`와 `SmallRecipeCard.kt`야. 다음 동작을 수행하는 테스트를 작성해줘: 1. 화면을 시작한다. 2. 검색창(Tag: 'SearchField')에 'chicken'을 입력한다. 3. 일치하지 않는 항목이 사라지는지 확인한다. 4. 남은 항목을 클릭했을 때 콜백이 트리거되는지 확인한다."*