## Side-effect

### Side-effect

#### Compose 기본 원칙

> Composable은 순수 함수여야 한다

- 외부 상태를 바꾸면 안 됨

#### Side-effect 정의

> Side-effect란 “Composable 스코프 밖의 앱 상태를 변경하는 행위”

- UI를 그리는 행위가 아님

#### 왜 Side-effect가 필요한가

> 현실의 앱은 “순수 함수”로만 만들 수 없다

- 화면에 Snackbar를 띄워야 함
- 다른 화면으로 이동해야 함
- 시스템 Back 버튼을 처리해야 함
- 생명주기에 맞춰 작업해야 함
- UI는 선언형이지만, 앱은 명령형 요소를 포함

### Compose 에서 Side-effect

#### 잘못된 예

```kotlin
@Composable
fun Screen() {
    snackbarHostState.showSnackbar("Hello") // ❌
}
```

- recomposition마다 실행
- 예측 불가
- 중복 실행
- 테스트 어려움
    - 그래서 Side-effect는 반드시 전용 API를 통해 실행

#### Effect API 역할

> Effect는 UI를 반환하지 않고, Composition이 “완료된 뒤” 부작용을 실행하는 Composable 함수

- Compose는 8가지 Effect API를 제공하여
    - “언제, 몇 번, 어떤 조건에서” 실행될지 명확히 제어하게 함

#### 핵심

- LaunchedEffect

```kotlin

```

- LaunchedEffect

```kotlin
LaunchedEffect(key) {
    // coroutine 실행
}
```

- 특정 key가 변경될 때 실행
- Composition 생명주기와 연결
- 가장 많이 쓰임
- 예:
    - Snackbar 표시
    - Flow collect
    - 초기 로드
    - 스크롤 감지

- SideEffect

```kotlin
SideEffect {
    // recomposition 이후 매번 실행
}
```

- 매 recomposition 후 실행
- 거의 안 씀
- 동기 Side-effect 용

- DisposableEffect

```kotlin
DisposableEffect(key) {
    onDispose {
        // 정리 작업
    }
}
```

- 리스너 등록/해제
- 시스템 리소스 정리
- 예:
    - BroadcastReceiver
    - Callback 등록
    - LaunchedEffect

- rememberCoroutineScope

```kotlin
val scope = rememberCoroutineScope()
```

- 사용자 이벤트 시 coroutine 실행
- Button 클릭 등

- rememberUpdatedState

```kotlin
val currentValue = rememberUpdatedState(onClick)

// 이후 effect 내부에서
currentOnClick.value()
```

- Effect 내부에서 최신 값을 참조하기 위해 사용
- Effect를 재시작하지 않고 값만 최신으로 유지

- (그 외: derivedStateOf, produceState, 등)

#### "Composable에는 라이프사이클이 없다”는 말의 정확한 의미

- Composable은 함수
- 클래스가 아님
- onCreate / onDestroy 없음

> 대신 Composition 진입 / 탈출 / recomposition 타이밍이 새로운 “UI 라이프사이클” <br/>
> Side-effect API는 이 가짜 라이프사이클을 명시적으로 다룰 수 있게 해주는 도구

### MVVM + Compose에서의 역할 분담

#### ViewModel이 잘하면 Side-effect가 줄어드는 이유

- ViewModel은
    - 상태(State)
    - 비즈니스 로직
    - 일회성 이벤트(Event)를 관리
- UI는 “언제 실행할지”만 판단하면 됨

#### 실제 앱에서 Side-effect를 두는 위치

- ScreenRoot에서 처리
    - 화면 단위의 Side-effect
    - 시스템 Back 버튼 처리 (BackHandler)
    - Navigation
    - Snackbar / Toast
    - 권한 요청
    - 화면 생명주기 관련 작업
    - 1회성 Event 처리
- 이유
    - 화면 전체의 컨텍스트 필요
    - ViewModel만으로는 처리 불가

- Component 내부에서 처리하는 것들
    - UI 동작에 가까운 Side-effect
    - 애니메이션 시작
    - 스크롤 감지
    - 포커스 요청