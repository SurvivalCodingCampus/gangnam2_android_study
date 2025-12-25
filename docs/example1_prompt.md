1. 기본 요구사항 입력

```markdown
 검색 + 결과 표시 통합 테스트를 할거야

     시나리오
      - 검색창에 키워드를 입력
      - 잠시 대기
      - Fake Repository에서 결과를 받아
      - 리스트로 화면에 표시


     테스트 요구사항
     - 텍스트 입력 → 버튼 클릭 → 리스트가 표시되는지
     - ViewModel ↔ Repository ↔ UI 연결 검증
     - 실제 서버 ❌, Fake Repository ⭕ (dev 또는 staging환경)

FakeRepository는 MockRecipeDataSourceImpl을 참고하면
될거야

@app/src/main/java/com/survivalcoding/gangnam2kiandroidstu
dy/presentation/screen/search_recipes/SearchRecipesScreen.
kt
@app/src/main/java/com/survivalcoding/gangnam2kiandroidstu
dy/data/data_source/MockRecipeDataSourceImpl.kt
@app/src/main/java/com/survivalcoding/gangnam2kiandroidstu
dy/presentation/screen/search_recipes/SearchRecipesViewMod
el.kt

```

2. 테스트 실패 알림과 함께 로그 입력

```markdown
search_dislays_filterd_results 테스트가 실패했어

java.lang.IllegalStateException: Hilt test,
com.survivalcoding.gangnam2kiandroidstudy.presentation.scr
een.search_recipes.SearchRecipesIntegrationTest, cannot
use a @HiltAndroidApp application but found
com.survivalcoding.gangnam2kiandroidstudy.AppApplication.
To fix, configure the test to use HiltTestApplication or a
custom Hilt test application generated with
@CustomTestApplication.
at
dagger.hilt.internal.Preconditions.checkState(Precondition
s.java:83)
at
dagger.hilt.android.internal.testing.MarkThatRulesRanRule.
<init>(MarkThatRulesRanRule.java:63)
at
dagger.hilt.android.testing.HiltAndroidRule.<init>(HiltAnd
roidRule.java:36)
at
com.survivalcoding.gangnam2kiandroidstudy.presentation.scr
een.search_recipes.SearchRecipesIntegrationTest.<init>(Sea
rchRecipesIntegrationTest.kt:40)

```

3. 테스트가 다시 실패하여 로그만 다시 보냄

```markdown
java.lang.AssertionError: Assert failed: The component
with Text + InputText + EditableText contains 'Traditional
spare ribs baked' (ignoreCase: false) is displayed!
at
androidx.compose.ui.test.AssertionsKt.assertIsNotDisplayed
(Assertions.kt:48)
at
com.survivalcoding.gangnam2kiandroidstudy.presentation.scr
een.search_recipes.SearchRecipesIntegrationTest.search_dis
plays_filtered_results(SearchRecipesIntegrationTest.kt:150
)
```