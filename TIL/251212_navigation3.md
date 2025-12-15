## Navigation

### Navigation3?

> Navigation3는 “컨트롤러 기반 네비게이션”이 아니라 “상태(State) 기반 네비게이션”

- ❌ NavController에게 “어디로 가라” 명령
- ✅ 현재 화면 상태를 나타내는 BackStack을 직접 관리

#### 핵심 구성 요소

- NavKey
    ```kotlin
    sealed interface Route : NavKey {
        data object Home : Route
        data object SearchRecipes : Route
    }
    ```
    - 화면을 식별하는 타입
    - String route ❌
    - enum ❌
    - 컴파일 타임 안전성 ⭕

- NavBackStack
    ```kotlin
    val mainBackStack = rememberNavBackStack(Route.Home)
    ```
    - 현재 화면 흐름을 나타내는 상태 리스트
    - 내부적으로는 MutableList<NavKey>
    - Navigation의 실체
- NavDisplay
    ```kotlin
    NavDisplay(
        backStack = mainBackStack,
        entryProvider = entryProvider {
            entry<Route.Home> { HomeRoot() }
            entry<Route.SearchRecipes> { SearchRecipesRoot() }
        }
    )
    ```
    - backStack의 마지막 요소를 보고
    - 해당하는 Composable을 렌더링

#### Navigation3 에서 이동하기

> 이동 = BackStack 변경

- Push
    ```kotlin
    mainBackStack.add(Route.SearchRecipes)
    ```
- Pop
    ```kotlin
    mainBackStack.removeAt(mainBackStack.lastIndex)
    ```
- Replace(탭 전환)
    ```kotlin
    mainBackStack.clear()
    mainBackStack.add(Route.Home)
    ```

#### Bottom Navigation과 Navigation3 핵심 원칙

> BottomTab = replace, <br/>
> Detail = push

```kotlin
BottomNavBar(
    onItemClick = { route ->
        mainBackStack.clear()
        mainBackStack.add(route)
    }
)
```

- 이전 탭 기록 ❌
- 항상 단일 루트 유지 ⭕

#### 왜 Screen에서는 navigate 하면 안 되는가?

- Screen은 재사용 가능해야 함
    - Navigation3는 상태 소유자 단일화가 핵심
    - navigate 로직이 흩어지면 디버깅 ❌ 테스트 ❌ 구조 붕괴

#### 뒤로가기 개념

```kotlin
if (mainBackStack.size > 1) {
    mainBackStack.removeAt(mainBackStack.lastIndex)
}
```

- 시스템 뒤로가기 / AppBar 뒤로가기 / 제스처 뒤로가기 모두 같은 로직으로 통합 가능

#### Navigation3의 핵심 장점

- 타입 안정성
- 잘못된 route 컴파일 타임 차단
- 상태 기반
- Compose 철학과 완전 일치
- 테스트 용이
- backStack 조작만 검증하면 됨
- 복잡한 UX에 강함
- BottomTab + Detail + Modal 조합 쉬움