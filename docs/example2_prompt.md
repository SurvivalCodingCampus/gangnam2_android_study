1. 기본 요구사항 입력

```markdown
리스트 → 상세 화면 네비게이션 테스트 작성해줘

시나리오

- 리스트 화면
- 특정 아이템 클릭
- 상세 화면으로 이동
- ID가 ViewModel에 제대로 전달되었는지 확인

테스트 요구사항

- Navigation 발생 여부
- 전달된 ID로 상세 데이터가 정상 표시되는지
- 뒤로 가기 시 상태 유지

아래 화면참고

@app/src/main/java/com/survivalcoding/gangnam2kiandroidstu
dy/presentation/screen/search_recipes/SearchRecipesScreen.
kt
@app/src/main/java/com/survivalcoding/gangnam2kiandroidstu
dy/presentation/screen/ingredient/IngredientScreen.kt
@app/src/main/java/com/survivalcoding/gangnam2kiandroidstu
dy/data/data_source/MockChefDataSourceImpl.kt
@app/src/main/java/com/survivalcoding/gangnam2kiandroidstu
dy/data/data_source/MockIngredientDataSourceImpl.kt
@app/src/main/java/com/survivalcoding/gangnam2kiandroidstu
dy/data/data_source/MockProcedureDataSourceImpl.kt

```

1화차 만에 성공

### 헷갈렸던 점

#### 테스트 코드에서 아래 비워진 이유

```kotlin
onBack = {
// Handle back from search if needed (not tested here) 
}
```

> “onBack 콜백이 호출됐는지”가 아니라“Back 결과로 Navigation 상태가 복구됐는지”를 검증

코드를 다시 보면 명백히 Back 이벤트를 발생

```kotlin
// 뒤로 가기 실행
composeRule.activityRule.scenario.onActivity { activity ->
    activity.onBackPressedDispatcher.onBackPressed()
}
```

그리고 그 다음

```kotlin
composeRule.waitUntil {
    composeRule.onAllNodesWithText("Tomatos")
        .fetchSemanticsNodes()
        .isEmpty()
}

composeRule.onNodeWithText("Traditional spare ribs baked")
    .assertIsDisplayed()
```

- Detail 화면 요소("Tomatos")가 사라짐
- Search 화면 요소가 다시 표시됨
- Back Navigation 성공
- 상태가 정상적으로 복구됨