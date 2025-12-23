## Event Driven Pattern

### 개념

`UI 상태(State)`: 화면에 “지속적으로” 반영되는 값
`UI 이벤트(Event)`: 한 번만 발생하고 소비되어야 하는 액션

이를 위해서 두개의 스트림을 분리해서 사용

| 구분  | 사용 Flow                   | 예시                           |
|-----|---------------------------|------------------------------|
| 상태  | `StateFlow`               | 로딩 여부, 입력값, 버튼 활성화           |
| 이벤트 | `SharedFlow` (또는 Channel) | Navigation, Snackbar, Dialog |

### StateFlow

> UI가 항상 관찰해야 하는 현재 상태

#### 특징

- 항상 현재 값을 보유
- 새 구독자는 즉시 최신 상태를 받음
- 화면 회전·재구성에도 안정적
- 사용 대상
    - 화면을 그리는 데 필요한 데이터
    - 버튼 활성화 여부
    - 텍스트 입력값
    - 로딩 / 에러 상태

#### 사용 예시

```kotlin
private val _state = MutableStateFlow(State())
val state = _state.asStateFlow()
```

### Event

> UI가 “반응”만 해야 하고 상태로 남으면 안 되는 신호

#### 특징

- 화면 이동
- Snackbar 표시
- Toast / Dialog
- 단발성 메시지
- ❌ 상태로 처리하면 안 되는 이유
    - 화면 회전 시 재실행됨
    - recomposition 시 다시 발생
    - 예측 불가능한 UI 버그 발생

#### 이벤트 표현 방식 — sealed interface

- 타입 안정성 확보
- when 식에서 모든 케이스 강제 처리
- 이벤트 종류가 명확히 드러남

```kotlin
sealed interface UiEvent {
    data object NavigateNext : UiEvent
    data class ShowMessage(val text: String) : UiEvent
}
```

### SharedFlow vs Channel

#### Channel → 누가 이벤트를 소비했는지 추적 어려움

| 특징    | 설명            |
|-------|---------------|
| 소비자   | **단일 소비자**    |
| 전달 방식 | 누가 받을지 보장 안 됨 |
| 용도    | 내부 코루틴 통신     |

#### SharedFlow → 특별한 이유가 없다면 SharedFlow를 사용

| 특징    | 설명                    |
|-------|-----------------------|
| 소비자   | **다수 구독자 가능**         |
| 전달 보장 | emit → collect 구조 명확  |
| 제어    | replay / buffer 설정 가능 |

### ViewModel 역할

> ViewModel은 UI를 직접 조작하지 않는다

#### 책임 분리

- ❌ ViewModel에서 Navigation 실행
- ❌ ViewModel에서 Snackbar 호출
- State와 Event를 “방출”만 한다
    ```kotlin
    private val _event = MutableSharedFlow<UiEvent>()
    val event = _event.asSharedFlow()
    ```

#### 구조 요약

```markdown
[ ViewModel ]
├─ StateFlow → UI 상태
└─ SharedFlow → UI 이벤트

[ Root / Screen ]
├─ collect State → 화면 렌더링
└─ collect Event → Navigation / Snackbar
```