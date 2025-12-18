## MVI Pattern

### MVI 개념

> MVI = Model · View · Intent

- UI는 단방향으로만 흐른다 (Unidirectional Data Flow)
- 사용자의 “의도(Intent)” → 상태(Model) 변경 → UI(View) 렌더링

### 각 구성 요소의 역할

### View(Screen / Root)

- 상태를 그리는 역할만
- 비즈니스 로직 ❌
- 상태 저장 ❌

```kotlin
@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit
)
```

- View는 State를 읽기만 한다
- 이벤트는 onAction으로만 전달

### Model → State (단일 UI 상태)

```kotlin
data class HomeState(
    val recipes: List<Recipe> = emptyList(),
    val newRecipes: List<NewRecipeUiModel> = emptyList(),
    val selectedCategory: HomeCategory = HomeCategory.ALL,
    val bookmarkedIds: Set<Int> = emptySet(),
    val isLoading: Boolean = false
)
```

### Intent → Action

- MVI에서 말하는 Intent = “사용자의 의도”
- Android에서는 보통 Action이라는 이름을 씀

```kotlin
sealed interface HomeAction {
    data class SelectCategory(val category: HomeCategory) : HomeAction
    data class ToggleBookmark(val recipeId: Int) : HomeAction
    data object SearchClicked : HomeAction
}
```

#### ViewModel

- Reducer 역할 (분기점)

```kotlin
fun onAction(action: HomeAction) {
    when (action) {
        is HomeAction.SelectCategory -> selectCategory(action.category)
        is HomeAction.ToggleBookmark -> toggleBookmark(action.recipeId)
        is HomeAction.SearchClicked -> navigateToSearch()
    }
}
```

#### 헷갈렸던 점

❌ MVI면 모든 컴포넌트에 Action 필요?
→ NO
Screen 단위까지만

❌ onAction은 Screen에서만 호출?
→ NO
Root에서도 호출 가능

❌ Action 으로 Navigation 넣어야 함?
→ NO
추후 SideEffect로 분리

### 흐름 요약

```markdown
[User Interaction]
↓
Action (의도)
↓
ViewModel
├─ Reduce State
├─ Call UseCase
└─ Emit SideEffect
↓
State (결과)
↓
View

```