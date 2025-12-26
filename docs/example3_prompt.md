1. 기본 요구사항 입력

```markdown
즐겨찾기 토글 + 상태 유지 테스트를 할거야

시나리오

- 리스트에서 ⭐ 즐겨찾기 버튼 클릭
- ViewModel 상태 변경
- 화면 재구성(recomposition) 후에도 상태 유지

테스트 요구사항

- 버튼 클릭 후 아이콘 상태 변경
- 화면 회전 or recomposition 상황에서도 상태 유지

아래 파일 참고

@app/src/main/java/com/survivalcoding/gangnam2kiandroidstudy/presentation/screen/home/HomeScreen.kt
@app/src/main/java/com/survivalcoding/gangnam2kiandroidstudy/presentation/screen/home/HomeViewModel.kt
```

2. 요구사항을 충족하지 않아 가이드 라인 제공

```kotlin
"즐겨찾기(Bookmark) 토글 + 상태 유지" 시나리오를 검증하려는 목적임
        하지만 현재 테스트가 문제 요구사항을 제대로 만족하고 있지 않음

[요구사항]
1.리스트에서 즐겨찾기 버튼 클릭
        2.ViewModel 상태 변경
3.버튼 클릭 후 아이콘 UI 상태 변경
4.화면 재구성 (recomposition 또는 Activity recreate) 이후에도 즐겨찾기 상태 유지

[현재 테스트 코드의 특징]
-HiltAndroidTest + Fake Repository 사용
-BookmarkRepository 상태를 직접 조회하여 검증
-UI 아이콘 변경 여부는 검증하지 않음
        -Activity recreate / rotation 검증을 생략함

        1.요구사항을 충족하도록 해줘
2.요구사항 확인시 필요한 테스트 태그가 필요하다면 달아도 좋아
        3.Repository 직접 접근 제거 (UI 결과로 검증)
3.통합 테스트외에도 Viewmodel 유닛 테스트가 필요해
        -즐겨찾기 클릭 시 addBookmark / removeBookmark 호출 여부
        -state 변경 (isBookmarked = true)
-초기 로딩 시 저장된 bookmark가 state에 반영되는지
```

3. 오류 발생 로그 제공

```markdown
java.lang.IllegalStateException: com.survivalcoding.gangnam2kiandroidstudy.MainActivity@6b76ca8 has
already set content. If you have populated the Activity with a ComposeView, make sure to call
setContent on that ComposeView instead of on the test rule; and make sure that that call to
`setContent {}` is done after the ComposeTestRule has run
at androidx.compose.ui.test.AndroidComposeUiTestEnvironment$AndroidComposeUiTestImpl.setContent(
ComposeUiTest.android.kt:844)
at androidx.compose.ui.test.junit4.AndroidComposeTestRule.setContent(
AndroidComposeTestRule.android.kt:420)
at androidx.compose.ui.test.junit4.StateRestorationTester.setContent(
StateRestorationTester.android.kt:54)
at
com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeScreenBookmarkTest.bookmark_toggles_ui_state_and_persists_after_recreation(
HomeScreenBookmarkTest.kt:138)
```

4. 요구사항 미충족 지적

```markdown
 // 7. Verify Persistence (Data Layer)
runBlocking {
val savedAfterRotation =
bookmarkRepository.getSavedRecipeIds()
assertTrue("Bookmark should persist after
rotation", savedAfterRotation.contains(1))
} 여기서 화면 회전 후 UI 가 여전히 Filled 인지
검증이 없어
```

5. 통합 테스트 화면 회전 검증 로직 지적

```markdown
이건 우회해서 검증한 거 잖아 나는 사용자가 실제 보고
있는 화면이 제대로 작동하고 있는지 검증하고 싶은 거야
화면이 회전하고 재성되는 게 아님 화면이 회전하고도 현재
화면의 데이터가 유지되어야 해
```

4. 로직은 바뀌었지만 SginIn 화면을 넘어가는 과정에서 에러가 발생하여 직접 수정함
    - Input filed 에 TestTag 를 달아서 테스트 친화적으로 변경
    - 추후 fake Auth 로 변경해줄 필요 있음