## 고급 상태 관리 기법

### Root / Screen 분리

#### 분리하는 이유

- 관심사의 명확한 분리 (Separation of Concerns)
    - **Root**는 화면의 "전체 흐름"을 관리하는 계층이
    - **Screen**은 "하나의 독립된 화면 UI"를 담당하는 계층
        - **Root = Navigation, 상태 공급, 의존성 조립**
        - **Screen = UI 그리기, ViewModel 을 통한 상태 소비**
- Navigation 구조를 UI와 분리하여 안정성 확보
    - Screen에 섞어버리면 UI 변경이 Navigation 로직을 깨뜨림
    - Screen 파일이 거대해짐
    - Testing 분리 불가
- ViewModel 생명주기를 안정적으로 관리
    - Root에서 ViewModel 을 생성하여 Screen에 전달
        - Route 단위 ViewModel 스코프가 보장됨
        - 같은 ViewModel을 여러 Screen에서 공유할 수도 있음 (예: MainRoot → 여러 Tab)
        - Screen은 ViewModel 생성 책임이 없어지고 테스트가 쉬워짐
- 테스트 용이성
- 구조적 확장성
    - Root를 두면 모든 플로우를 모듈 단위로 분리 가능

#### Root의 역할

- ViewModel 의존성 주입
- State 구독하여 Screen에 전달
- 화면 이동 처리
- Dialog / Snackbar 등 1회성 이벤트 처리
- Lifecycle + remember 등 초기화 처리
- 권한 요청 등 외부 시스템 연결
- Preview나 UI 테스트를 방해하는 모든 로직 이동

## Screen의 역할

- 상태 기반 UI 렌더링
- 유저 입력 → Root(ViewModel)로 이벤트 콜백 전달
- ViewModel 을 직접 생성하거나 알고 있어서는 안 됨