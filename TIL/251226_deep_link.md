## Deep Link & App LinK

### Deep Link

> **외부에서 앱 내부의 특정 화면으로 바로 이동**하기 위한 링크

- 웹, 이메일, 알림, adb, 다른 앱 등에서 호출 가능
- 앱이 설치되어 있으면 앱 실행
- 앱이 없으면 아무 동작도 하지 않음 (기본 Deep Link 기준)

#### Deep Link 특징

- 커스텀 scheme 사용 (`myapp://`)
- 비교적 구현이 간단
- 보안 검증 없음
- 동일 scheme을 사용하는 다른 앱이 있으면 충돌 가능

### Android Deep Link 구현 방법

#### AndroidManifest.xml 설정

```xml

<intent-filter>
    <action android:name="android.intent.action.VIEW" />

    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />

    <data android:scheme="myapp" android:host="recipes" />
</intent-filter>
```

- scheme: 앱 전용 프로토콜
- host: 라우팅 기준
- path는 코드에서 직접 파싱

#### MainActivity에서 딥링크 수신

```kotlin
private var deepLinkUri by mutableStateOf<String?>(null)

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    deepLinkUri = intent.dataString
}

override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    deepLinkUri = intent.dataString
}
```

- cold start → onCreate
- warm start → onNewIntent
- 상태(State)로 관리해야 Compose에 반영됨

#### Compose + Navigation3 처리 핵심

- 핵심 문제
    - Activity는 재생성되지 않음 (WARM start)
    - ememberNavBackStack는 한 번 생성되면 유지됨
- 해결 전략
    - key(deepLinkUri)로 Composable 강제 재생성
    - 딥링크는 “초기 상태”가 아니라 스택 재구성 명령

#### NavigationRoot

```kotlin
entry<Route.Main> {
    key(deepLinkUri) {
        MainRoot(deepLinkUri = deepLinkUri)
    }
}
```

- deepLinkUri 변경 시 MainRoot 완전 재생성
- 내부 remember 상태 초기화

#### MainRoot에서 스택 재구성

```kotlin
val mainBackStack = rememberNavBackStack(Route.Home)

LaunchedEffect(deepLinkUri) {
    if (deepLinkUri != null) {
        val uri = deepLinkUri.toUri()
        val path = uri.pathSegments

        when {
            path.contains("saved") -> {
                mainBackStack.clear()
                mainBackStack.add(Route.SavedRecipes)
            }
            path.contains("detail") -> {
                val id = path.getOrNull(1)?.toIntOrNull()
                if (id != null) {
                    mainBackStack.clear()
                    mainBackStack.add(Route.Home)
                    mainBackStack.add(Route.RecipeDetail(id))
                }
            }
        }
    }
}
```

- BackStack 생성과 딥링크 처리를 분리
- 딥링크는 명령(Command)로 처리
- 뒤로가기 UX까지 제어 가능

### App Link

> App Link는 HTTP/HTTPS URL을 앱과 안전하게 연결하는 Android 공식 방식

- 앱이 설치되어 있으면 앱 실행
- 앱이 없으면 웹 페이지 열림
- 도메인 소유권을 Digital Asset Links로 검증

#### DeepLink AppLink 비교

| 항목      | Deep Link | App Link |
|---------|-----------|----------|
| URL     | myapp://  | https:// |
| 보안 검증   | 없음        | 있음       |
| 앱 미설치 시 | 실패        | 웹 열림     |
| 충돌 가능성  | 있음        | 없음       |
| 실서비스 권장 | ❌         | ✅        |

### App Link 구현 방법

#### 로컬 폴더와 연결

- `firebase init hosting`

#### AndroidManifest.xml 설정

```xml

<intent-filter android:autoVerify="true">
    <action android:name="android.intent.action.VIEW" />

    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />

    <data android:scheme="https" android:host="example.com" />
</intent-filter>
```

- autoVerify="true": 도메인 검증 요청
- HTTPS 필수

#### assetlinks.json 작성

위치 : Public -> .well-known

```json
[
  {
    "relation": [
      "delegate_permission/common.handle_all_urls"
    ],
    "target": {
      "namespace": "android_app",
      "package_name": "com.example.app",
      "sha256_cert_fingerprints": [
        "AA:BB:CC:..."
      ]
    }
  }
]

```

- 공개 파일 (GitHub 커밋 가능)
- 인증서 SHA-256 지문 필요 : `./gradlew signingReport`

#### Fire base hosting 배포

- `firebase deploy` 후 웹 접근 가능해야 함