# 레시피 상세 화면 네비게이션 구현 (1단계)

## 1. 개요
이 문서는 검색 결과 화면(`SearchRecipesScreen`)에서 개별 레시피를 클릭했을 때 상세 화면으로 이동하기 위한 기초 작업을 기술합니다. UI 이벤트 발생부터 ViewModel 처리, 그리고 이를 검증하는 테스트 코드 작성까지의 과정을 다룹니다.

## 2. 작업 목표
*   사용자가 리스트의 레시피 카드(`RecipeCardMedium`)를 클릭하면 `OnRecipeClick` 액션이 발생해야 함.
*   해당 액션이 ViewModel 및 상위 컴포넌트로 전달될 수 있도록 구조 마련.
*   통합 테스트(Instrumented Test)를 통해 클릭 이벤트가 정상적으로 발생하는지 검증.

## 3. 주요 변경 사항

### 3.1 Action 정의 (`SearchRecipesAction.kt`)
레시피 클릭 이벤트를 처리하기 위해 새로운 액션을 추가했습니다.
```kotlin
sealed interface SearchRecipesAction {
    // ... 기존 액션들
    data class OnRecipeClick(val recipeId: Long) : SearchRecipesAction
}
```

### 3.2 UI 이벤트 연결 (`SearchRecipesScreen.kt`)
`LazyVerticalGrid` 내의 `RecipeCardMedium` 컴포넌트에 클릭 리스너를 추가하여, 클릭 시 위에서 정의한 액션을 호출하도록 수정했습니다.
```kotlin
items(recipes) { recipe ->
    RecipeCardMedium(
        recipe,
        modifier = Modifier.clickable {
            onAction(SearchRecipesAction.OnRecipeClick(recipe.id))
        }
    )
}
```

### 3.3 ViewModel 처리 (`SearchRecipesViewModel.kt`)
새로 추가된 `OnRecipeClick` 액션에 대한 분기 처리를 추가하여 컴파일 오류를 방지했습니다. (현재는 빈 블록이며, 추후 실제 네비게이션 로직 구현 필요)
```kotlin
fun onAction(action: SearchRecipesAction, navigateBack: () -> Unit) {
    when (action) {
        // ...
        is SearchRecipesAction.OnRecipeClick -> {
            // TODO: 상세 화면 네비게이션 로직 구현
        }
    }
}
```

## 4. 테스트 검증

### 4.1 테스트 케이스 추가 (`SearchRecipesScreenTest.kt`)
`triggerRecipeClick` 테스트를 추가하여 다음 시나리오를 검증했습니다.
1.  화면에 레시피("Traditional Spare Ribs")가 표시됨.
2.  해당 텍스트를 가진 노드를 클릭 (`performClick()`).
3.  `onAction` 람다를 통해 `OnRecipeClick(id)` 액션이 전달되는지 확인.

### 4.2 실행 결과
`connectedAndroidTest` 실행 결과, 모든 테스트가 통과하였습니다.

## 5. 향후 계획
*   **Navigation 구현**: `SearchRecipesRoot` 및 `NavGraph`에서 `OnRecipeClick` 이벤트를 받아 실제 상세 화면(`RecipeDetailScreen`)으로 이동하는 로직 구현.
*   **Argument 전달**: 레시피 ID를 네비게이션 인자로 전달하는 로직 추가.
