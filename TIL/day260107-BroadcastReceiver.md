# Broadcast Receiver

### Broadcast Receiver란 무엇일까요?

Broadcast Receiver(브로드캐스트 수신기)는 Android 시스템 또는 다른 앱에서 보내는 **브로드캐스트 메시지**를 수신하고 그에 반응하는 Android의 4대 컴포넌트 중 하나입니다.

*   **"메시지를 보낸다" (Broadcast):** 시스템 전체에 "에어플레인 모드가 켜졌어!", "배터리가 부족해!", "네트워크 연결이 끊겼어!" 와 같은 이벤트를 알리는 것을 의미합니다.
*   **"메시지를 받는다" (Receive):** 내 앱이 이러한 시스템의 알림을 듣고 있다가, 특정 알림이 오면 미리 정해둔 동작(예: "에어플레인 모드이면 오프라인 모드로 전환")을 수행하는 것입니다.

과거에는 앱이 설치만 되어 있어도 종료된 상태에서 특정 브로드캐스트를 수신하여 실행될 수 있었습니다. 하지만 이로 인해 배터리가 과도하게 소모되고 시스템 성능이 저하되는 문제가 발생했습니다.

### 최신 Android에서의 변화 (중요!)

**Android 8.0 (API 레벨 26) 부터** 대부분의 브로드캐스트는 더 이상 `AndroidManifest.xml`에 등록(정적 등록)하는 방식으로 수신할 수 없습니다. 이는 앱이 실행 중이지 않은 상태에서 불필요하게 깨어나는 것을 방지하기 위함입니다.

*   **정적 등록 (Manifest-declared):** `AndroidManifest.xml`에 리시버를 등록하는 방식. 현재는 부팅 완료(`ACTION_BOOT_COMPLETED`)나 일부 시스템 암시적 브로드캐스트 등 **극히 예외적인 경우에만 허용**됩니다.
*   **동적 등록 (Context-registered):** 앱이 실행 중일 때(Activity, Service 등 Context가 활성화된 동안) 코드를 통해 직접 리시버를 등록하고 해제하는 방식입니다. **현재 권장되는 방식**입니다.

따라서 최신 앱 개발에서는 **동적 등록**을 기본으로 사용한다고 생각하시면 됩니다.

---

### 예제: 에어플레인 모드 변경 감지하기 (Jetpack Compose & 동적 등록)

가장 대표적인 시스템 브로드캐스트 중 하나인 '에어플레인 모드' 변경을 감지하여 화면에 상태를 표시하는 예제를 만들어 보겠습니다.

#### 1. BroadcastReceiver 클래스 생성

먼저 브로드캐스트를 수신했을 때 어떤 동작을 할지 정의하는 클래스를 만듭니다.

**`MyAirplaneModeReceiver.kt`**

```kotlin
package com.survivalcoding.gangnam2kiandroidstudy.presentation.receiver // 예시 경로입니다.

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

// BroadcastReceiver를 상속받는 클래스를 만듭니다.
class MyAirplaneModeReceiver : BroadcastReceiver() {

    // onReceive 메서드는 브로드캐스트가 수신될 때마다 호출됩니다.
    override fun onReceive(context: Context?, intent: Intent?) {
        // 인텐트가 null이 아니고, 액션이 '에어플레인 모드 변경'일 때만 동작하도록 합니다.
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            // 'state'라는 이름의 boolean extra로 에어플레인 모드 활성화 여부를 가져옵니다.
            val isAirplaneModeOn = intent.getBooleanExtra("state", false)

            val message = if (isAirplaneModeOn) {
                "에어플레인 모드가 켜졌습니다."
            } else {
                "에어플레인 모드가 꺼졌습니다."
            }
            
            // 간단하게 Toast 메시지를 띄워 사용자에게 상태를 알립니다.
            // 실제 앱에서는 ViewModel의 상태를 업데이트하는 등의 로직을 처리합니다.
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
```

#### 2. Composable에서 리시버 동적 등록 및 해제

이제 화면(Composable)이 보여질 때 리시버를 등록하고, 화면이 사라질 때 해제하여 메모리 누수를 방지합니다.

**`AirplaneModeScreen.kt`**

```kotlin
package com.survivalcoding.gangnam2kiandroidstudy.presentation.main // 예시 경로입니다.

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.survivalcoding.gangnam2kiandroidstudy.presentation.receiver.MyAirplaneModeReceiver

@Composable
fun AirplaneModeScreen() {
    val context = LocalContext.current
    
    // DisposableEffect는 Composable이 Composition에서 제거될 때(화면에서 사라질 때)
    // onDispose 블록을 실행시켜주는 역할을 합니다. 리소스를 정리하기에 완벽한 장소입니다.
    DisposableEffect(context) {
        // 1. 리시버 인스턴스를 생성합니다.
        val receiver = MyAirplaneModeReceiver()
        
        // 2. 인텐트 필터를 생성합니다. 어떤 브로드캐스트를 수신할지 지정합니다.
        // 여기서는 '에어플레인 모드 변경' 액션을 지정합니다.
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        
        // 3. context.registerReceiver()를 사용하여 리시버를 시스템에 등록합니다.
        // 이제부터 에어플레인 모드가 변경되면 MyAirplaneModeReceiver의 onReceive가 호출됩니다.
        context.registerReceiver(receiver, filter)
        
        // 4. onDispose: Composable이 화면에서 사라질 때 호출되는 정리(cleanup) 블록입니다.
        onDispose {
            // 반드시 unregisterReceiver()를 호출하여 리시버 등록을 해제해야 합니다.
            // 그렇지 않으면 앱이 종료되어도 리시버가 메모리에 남아 메모리 누수(Memory Leak)가 발생합니다.
            context.unregisterReceiver(receiver)
        }
    }
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "에어플레인 모드를 켜거나 꺼보세요.")
    }
}
```

#### 3. AndroidManifest.xml (권한 추가)

에어플레인 모드 변경 상태를 수신하려면 `CONNECTIVITY_CHANGE` 권한이 필요할 수 있습니다. (주로 네트워크 상태 변경에 필요하지만, 명시해주는 것이 좋습니다.)

**`AndroidManifest.xml`**

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.survivalcoding.gangnam2kiandroidstudy">

    <!-- 네트워크 상태 변경 및 연결 관련 브로드캐스트를 수신하기 위한 권한 -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    <application
        ...>
        <!-- ...기존 Activity 설정들... -->
    </application>

</manifest>
```

*   `MyAirplaneModeReceiver`는 `AndroidManifest.xml`에 등록하지 **않았습니다**. 이것이 바로 동적 등록의 핵심입니다.

이제 앱을 실행하고 `AirplaneModeScreen`이 보이는 상태에서 상단 알림창을 내려 에어플레인 모드를 켜거나 끄면 "에어플레인 모드가 켜졌습니다/꺼졌습니다" 라는 Toast 메시지가 나타나는 것을 확인할 수 있습니다.

### 정리 및 저의 생각

사용자님의 생각이 정확합니다. Android 개발, 특히 Broadcast Receiver 같은 시스템 레벨의 기능은 과거의 정보가 현재는 동작하지 않거나, 심지어 "잘못된 방식"으로 취급받는 경우가 많습니다.

**핵심 요약:**

1.  **동적 등록이 기본:** 앱의 생명주기(Lifecycle)에 맞춰 필요한 시점에만 리시버를 등록하고, 화면을 벗어나면 반드시 해제하는 것이 현대적인 방식입니다. `DisposableEffect` (Compose) 나 `LifecycleObserver` (View)를 사용하는 것이 좋습니다.
2.  **정적 등록은 예외:** `AndroidManifest.xml`에 등록하는 방식은 부팅 완료 감지 등 정말 특별한 케이스가 아니면 사용하지 않습니다. 시스템에 부담을 주기 때문입니다.
3.  **권한 확인:** 수신하려는 브로드캐스트에 따라 `AndroidManifest.xml`에 적절한 `<uses-permission>`을 추가해야 할 수 있습니다.

이처럼 최신 가이드라인을 따르면 배터리 효율적이고 안정적인 앱을 만들 수 있습니다. 궁금한 점이 있다면 언제든지 다시 질문해 주세요.
