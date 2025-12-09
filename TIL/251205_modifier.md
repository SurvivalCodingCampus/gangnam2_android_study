## Modifier

### Modifier

> Modifier는 Jetpack Compose에서 UI 요소의 크기, 동작, 모양 등을 수정하고 꾸미는 데 사용

#### Modifier 특징

- `체이닝 방식`: 여러 Modifier를 점(.)으로 연결하여 사용
- `순서 중요성`: 적용되는 순서에 따라 다른 결과가 나타남
- `재사용 가능`: 변수나 함수로 만들어 재사용 가능
- `확장 가능`: 커스텀 Modifier 생성 가능

### Focus

#### Focus 관련

| 이름                          | 역할                              |
|-----------------------------|---------------------------------|
| `FocusRequester`            | 명시적으로 특정 컴포넌트에 포커스 요청           |
| `Modifier.focusRequester()` | FocusRequester가 특정 UI에 작동하도록 연결 |
| `LocalFocusManager.current` | 전체 Focus 상태 관리                  |
| `focusManager.clearFocus()` | 포커스 제거(키보드 숨김 시 같이 사용됨)         |
| `.onFocusChanged {}`        | 포커스 상태 변할 때 호출되는 콜백             |

#### Keyboard (IME: Input Method Editor)

| 구분                              | 설명                            |
|---------------------------------|-------------------------------|
| IME                             | 필드 성격에 따라 자동으로 모양이 바뀜         |
| 키보드 Action (Done/Next/Search 등) | 사용자가 입력 완료를 나타냄 → 특정 동작 실행 가능 |

#### KeyboardOptions

| 속성             | 예시                   | 설명           |
|----------------|----------------------|--------------|
| `keyboardType` | `KeyboardType.Email` | @ 있는 이메일 키보드 |
| `imeAction`    | `ImeAction.Next`     | Next 버튼 표시   |

- 예 : Email 입력시 적절한 키보드
    ```Kotlin
    keyboardOptions = KeyboardOptions(
    keyboardType = KeyboardType.Email,
    imeAction = ImeAction.Next
    )
    ```

#### KeyboardActions

| 액션       | 동작                 |
|----------|--------------------|
| `onNext` | 다음 필드로 포커스 이동      |
| `onDone` | 포커스 해제 + 로그인/제출 진행 |

#### Focus 흐름

- FocusRequester 생성
- 다음 컴포넌트에 연결
- IMEAction 설정
- KeyboardActions에서 요청
    - 예시
      ````kotlin
      val passwordFocusRequester = remember { FocusRequester() }
      val focusManager = LocalFocusManager.current
    
      InputField(
          keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
          keyboardActions = KeyboardActions(
              onNext = { passwordFocusRequester.requestFocus() }
          )
      )
    
      InputField(
          modifier = Modifier.focusRequester(passwordFocusRequester),
          keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
          keyboardActions = KeyboardActions(
              onDone = {
                  focusManager.clearFocus()
              }
          )
      )
      ```