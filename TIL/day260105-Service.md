# 4대 컴포넌트 - Service

## 1. 주요 유형 (Types)

Android 서비스는 앱의 UI 없이 백그라운드에서 오래 실행되는 작업을 수행하기 위한 컴포넌트입니다. 크게 세 가지 유형으로 나눌 수 있습니다.

### 1) Foreground Service (포어그라운드 서비스)

사용자가 현재 서비스가 실행 중임을 인지할 수 있도록 **상태 표시줄에 지워지지 않는 알림(Notification)을 반드시 표시**해야 하는 서비스입니다. 사용자가 앱과 상-호작용하지 않을 때도 계속 실행되어야 하는 작업에 적합합니다.

- **사용 사례**: 음악 플레이어, 피트니스 앱의 운동 기록, 파일 다운로드 등
- **특징**: 시스템이 메모리 부족 상태일 때도 서비스를 강제 종료하지 않으려고 마지막까지 시도합니다.
- **주의사항**: Android 14(API 34)부터는 `AndroidManifest.xml` 파일에 서비스의 목적에 맞는 `foregroundServiceType`을 반드시 선언해야 합니다.

```xml
<!-- AndroidManifest.xml -->
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
<!-- Android 14+ 에서는 서비스 유형에 맞는 권한도 추가해야 합니다. -->
<uses-permission android:name="android.permission.FOREGROUND_SERVICE_MEDIA_PLAYBACK" />

<service
    android:name=".MyForegroundService"
    android:foregroundServiceType="mediaPlayback" />
```

### 2) Background Service (백그라운드 서비스)

사용자에게 직접 보이지 않는 백그라운드에서 작업을 수행하는 서비스입니다. 하지만 Android 8.0(API 26)부터 백그라운드 실행에 대한 제약이 매우 엄격해져서, **대부분의 경우 `WorkManager`와 같은 다른 API로 대체**되었습니다.

- **사용 사례 (과거)**: 데이터 동기화, 로그 전송 등
- **현재**: 앱이 포어그라운드에 있을 때만 실행되는 단기 작업을 제외하고는 거의 사용되지 않습니다. 지연 실행이 가능하거나 실행 보장이 필요한 작업에는 `WorkManager` 사용이 강력히 권장됩니다.

### 3) Bound Service (바운드 서비스)

액티비티나 다른 컴포넌트와 연결(bind)되어 데이터를 주고받는 클라이언트-서버 형태의 서비스입니다. 연결된 컴포넌트가 하나라도 있는 동안에만 실행되며, 모든 컴포넌트와의 연결이 끊어지면 서비스는 자동으로 소멸됩니다.

- **사용 사례**: 여러 컴포넌트에서 공유해야 하는 기능이나 상태를 관리할 때 (예: 실시간 위치 정보 제공, 음악 재생 제어)
- **특징**: `bindService()`를 통해 컴포넌트와 연결되고, `onBind()` 콜백에서 `IBinder` 인터페이스를 반환하여 통신합니다.

## 2. 수명 주기 (Lifecycle)

서비스는 '시작됨(Started)' 상태와 '연결됨(Bound)' 상태, 또는 두 가지 모두일 수 있으며, 이에 따라 수명주기가 달라집니다.

### 1) Started Service의 수명 주기

`startService()` 호출로 시작되고, `stopSelf()` 또는 `stopService()`가 호출될 때까지 독립적으로 실행됩니다.

- `onCreate()`: 서비스가 처음 생성될 때 호출됩니다. (초기 설정)
- `onStartCommand()`: `startService()`가 호출될 때마다 실행됩니다. 서비스의 실제 작업을 여기서 수행합니다.
- `onDestroy()`: 서비스가 소멸될 때 호출됩니다. (리소스 정리)

```kotlin
// MyStartedService.kt

class MyStartedService : Service() {

    // 서비스가 처음 생성될 때 한 번만 호출됩니다.
    override fun onCreate() {
        super.onCreate()
        Log.d("MyStartedService", "onCreate()")
    }

    // startService()가 호출될 때마다 호출됩니다.
    // 여기서 서비스의 주요 로직을 수행합니다.
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyStartedService", "onStartCommand()")

        // 시간이 오래 걸리는 작업은 별도의 스레드에서 처리해야 합니다.
        // 그렇지 않으면 앱의 UI 스레드를 막아 ANR(Application Not Responding)을 유발할 수 있습니다.
        Thread {
            Log.d("MyStartedService", "백그라운드 작업 시작")
            Thread.sleep(5000) // 5초간 작업 시뮬레이션
            Log.d("MyStartedService", "백그라운드 작업 완료")

            // 작업이 끝나면 서비스를 스스로 중지시킵니다.
            // startId는 onStartCommand가 호출될 때마다 고유하게 부여되는 ID로,
            // 여러 요청 중 최신 요청에 대해서만 중지하도록 보장할 때 사용될 수 있습니다.
            stopSelf(startId)
        }.start()

        // START_STICKY: 서비스가 시스템에 의해 종료되어도 메모리가 확보되면 다시 생성하고 onStartCommand를 호출 (intent는 null)
        // START_NOT_STICKY: 서비스가 시스템에 의해 종료되면 다시 생성하지 않음
        // START_REDELIVER_INTENT: 서비스가 종료되어도 다시 생성하고 마지막 intent를 그대로 전달
        return START_STICKY
    }

    // 서비스가 소멸될 때 호출됩니다.
    // 리소스 해제 등의 정리 작업을 수행합니다.
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyStartedService", "onDestroy()")
    }

    // Bound Service가 아니므로 null을 반환합니다.
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
```

### 2) Bound Service의 수명 주기

`bindService()` 호출로 컴포넌트와 연결됩니다. 모든 클라이언트가 연결을 해제(`unbindService()`)하면 서비스는 소멸됩니다.

- `onCreate()`: 서비스가 처음 생성될 때 호출됩니다.
- `onBind()`: 클라이언트가 `bindService()`를 호출하여 연결을 시도할 때 호출됩니다. 통신을 위한 `IBinder`를 반환해야 합니다.
- `onUnbind()`: 모든 클라이언트의 연결이 해제되었을 때 호출됩니다.
- `onDestroy()`: 서비스가 소멸될 때 호출됩니다.

```kotlin
// MyBoundService.kt

class MyBoundService : Service() {

    // 클라이언트와 통신하기 위한 Binder 객체
    private val binder = LocalBinder()
    private val randomNumberGenerator = java.util.Random()

    // 클라이언트가 서비스에 접근할 수 있도록 하는 Binder 클래스
    inner class LocalBinder : Binder() {
        // 서비스의 인스턴스를 반환하여 클라이언트가 public 메서드를 호출할 수 있게 함
        fun getService(): MyBoundService = this@MyBoundService
    }

    // 클라이언트가 서비스와 바인드될 때 호출됩니다.
    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    // 클라이언트가 호출할 수 있는 public 메서드
    fun getRandomNumber(): Int {
        return randomNumberGenerator.nextInt(100)
    }
}
```
```kotlin
// Activity에서 BoundService 사용 예시

class MyActivity : AppCompatActivity() {

    private var myBoundService: MyBoundService? = null
    private var isBound = false

    // 서비스와의 연결 상태를 감지하는 ServiceConnection
    private val connection = object : ServiceConnection {
        // 서비스와 성공적으로 연결되었을 때 호출
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as MyBoundService.LocalBinder
            myBoundService = binder.getService()
            isBound = true
            Log.d("MyActivity", "Service Connected")
        }

        // 서비스와의 연결이 예기치 않게 끊어졌을 때 호출 (정상적인 unbind에서는 호출되지 않음)
        override fun onServiceDisconnected(arg0: ComponentName) {
            isBound = false
            Log.d("MyActivity", "Service Disconnected")
        }
    }
    
    // 버튼 클릭 시 서비스의 메서드 호출
    fun onButtonClick() {
        if (isBound) {
            val num = myBoundService?.getRandomNumber()
            Log.d("MyActivity", "Random number from service: $num")
        }
    }

    override fun onStart() {
        super.onStart()
        // 서비스에 바인딩
        Intent(this, MyBoundService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        // 서비스로부터 언바인딩
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }
}
```

## 3. 최신 개발 트렌드 및 주의사항

### 1) WorkManager 사용 권장

Android는 대부분의 백그라운드 작업 처리를 위해 **`WorkManager`** 라이브러리를 강력히 권장합니다. `WorkManager`는 지연 가능하고(deferrable), 기기 상태(네트워크 연결, 충전 상태 등)에 따라 실행 조건을 설정할 수 있어 배터리 소모에 효율적입니다.

- **`Service`를 써야 할 때**: 즉시 실행되어야 하고 사용자가 인지해야 하는 작업 (예: 음악 재생, 전화 통화). 이 경우 **Foreground Service**를 사용해야 합니다.
- **`WorkManager`를 써야 할 때**: 즉시 실행될 필요는 없지만 반드시 실행이 보장되어야 하는 작업 (예: 주기적인 데이터 동기화, 서버에 로그 전송).

### 2) 백그라운드 실행 제한

Android 8.0 (API 26)부터 앱이 백그라운드 상태일 때 일반적인 `startService()`를 사용하면 시스템이 몇 분 내에 서비스를 중지시키며, `IllegalStateException`이 발생할 수 있습니다.

- **해결책**: 백그라운드에서 작업을 계속하려면 `Context.startForegroundService()`를 호출하여 서비스를 시작하고, 서비스 내에서 5초 이내에 `startForeground()`를 호출하여 사용자에게 보이는 알림을 표시해야 합니다. 이를 통해 서비스를 포어그라운드 상태로 승격시킬 수 있습니다.

### 3) Foreground Service 유형 선언 (Android 14+)

Android 14를 타겟으로 하는 앱부터는 `AndroidManifest.xml`에 `<service>` 태그의 속성으로 `android:foregroundServiceType`을 명시해야 합니다.

- **목적**: 서비스의 목적을 시스템에 명확히 알려, 특정 유형의 서비스에만 허용되는 권한이나 리소스 접근을 관리하기 위함입니다.
- **예시**: 위치 정보를 추적하는 서비스는 `location`, 카메라를 사용하는 서비스는 `camera`, 데이터 동기화는 `dataSync` 등으로 지정해야 합니다. 또한, 타입에 맞는 `FOREGROUND_SERVICE_*` 권한도 함께 선언해야 합니다.
- **미지정 시**: `MissingForegroundServiceTypeException`이 발생하며 앱이 비정상 종료될 수 있습니다.

```xml
<!-- AndroidManifest.xml: 위치 정보 포어그라운드 서비스 예시 -->
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE_LOCATION" />

<service
    android:name=".tracking.LocationTrackingService"
    android:foregroundServiceType="location"
    android:exported="false" />
```
