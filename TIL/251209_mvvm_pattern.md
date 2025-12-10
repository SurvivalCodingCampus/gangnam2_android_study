## MVVM Pattern

### MVVM 개요

> 앱을 개발한다면 앱의 확장성과 유지보수의 편의성을 고려해서 꼭 아키텍처를 적용하는 것이 필요 <br/>
> 현재 모바일 앱에 가장 적합한 아키텍처 중 하나가 MVVM

#### 비즈니스 로직을 처리하는 클래스 : Repository

> 데이터의 출처(로컬 DB, 서버 API, 파일 등)에 상관없이 일관된 인터페이스로 데이터 입출력을 담당

#### UI 관련 비즈니스 로직을 처리하는 클래스 : ViewModel

> UI에서 필요한 데이터를 Repository로부터 받아 가공하고, 화면 상태를 관리하며, UI 로직을 처리해 View와 비즈니스 로직을 분리하는 계층

#### MVVM 각 레이어의 역할

- View
    - 사용자가 실제로 보는 UI 화면
    - ViewModel의 상태를 자동으로 반영해 UI 업데이트
    - 사용자 액션을 ViewModel에 전달
        - 예: `Composable`, `Activity`, `Fragment`
- ViewModel
    - UI 비즈니스 로직 담당
    - 화면에서 실행해야 하는 동작을 정의
    - 데이터 변경 시 View에 상태 알려줌 (`StateFlow`, `LiveData` 등 사용)
    - View와 Model 사이에서 **중개** 역할
        - 예: `SavedRecipesViewModel`
- Model (Repository 포함)
- **데이터 비즈니스 로직 담당**
- 서버 API, DB, 파일 등 모든 데이터 소스 관리
- ViewModel에서 요청한 데이터를 전달
- 예: `RecipeRepository`, `Local DB`, `Network API`
- 흐름 정리
    ```
    View  →  사용자 액션 전달  →  ViewModel  
    ViewModel  →  데이터 요청  →  Model  
    Model  →  가공한 데이터 전달  →  ViewModel  
    ViewModel  →  상태 업데이트  →  View(UI 반영)
    ```

### ViewModel

#### ViewModel의 역할

> ViewModel은 단지 화면(View) 하나에 대한 로직(Model) 일 뿐 </br>
> → 하나의 화면(View)에 하나의 ViewModel 이 일반적

- 모델(일반적으로 Repository)에서 제공한 데이터를 UI로 표시하기 쉬운 형태로 변환해 주는 역할 (복잡한 로직을 숨겨주는)
    - 화면의 상태(변수)를 캡슐화
    - 화면에 표시할 데이터
    - 여러가지 상태 (로딩 등)
- View 에서 발생하는 액션(이벤트) 처리의 캡슐화

#### 안드로이드에서 ViewModel

> Android에서 제공하는 UI 상태 홀더(UI State Holder) </br>
> → 화면 회전 등 Activity 생명주기 변화에도 UI 상태 유지가 가능한 클래스

| 특징          | 설명                               |
|-------------|----------------------------------|
| 생명주기 영향 없음  | 화면 회전/백스택 이동에도 데이터 보존            |
| 데이터 바인딩의 핵심 | View에 바인딩된 상태가 변경되면 자동 UI 반영     |
| 비즈니스 로직 캡슐화 | View는 UI에만 집중, 로직은 ViewModel이 담당 |

```kotlin
class CounterViewModel : ViewModel() {

    // UI가 관찰하는 공개 상태
    val count = _count.asStateFlow()

    // 내부에서만 수정 가능한 상태
    private var _count = MutableStateFlow(0)

    // 사용자 액션에 대한 비즈니스 로직
    fun increment() {
        _count.value++
    }
}
```

| 요소                 | 역할                      |
|--------------------|-------------------------|
| `MutableStateFlow` | 내부 상태 보관 및 변경           |
| `.asStateFlow()`   | 외부에 읽기 전용 형태로 노출        |
| `ViewModel()`      | Android 생명주기와 독립된 상태 보존 |
| `increment()`      | UI가 요청하는 기능 정의          |

#### ViewModel 인스턴스를 얻는 법

> ViewModel은 Compose에서 직접 new()로 만들지 않음 </br>
> → Android가 제공하는 ViewModelStore 안에서 </br>
> → 생명주기가 보장되도록 관리되어야 하기 때문

- MainActivity
    - 화면 회전해도 ViewModel 인스턴스를 동일하게 유지
    - ViewModel이 Activity에 생명주기 안정적으로 붙어서 동작
      ```kotlin
      val viewModel: MainViewModel by viewModels {
          MainViewModel.Factory
      }
      ```
      | 내용                  | 설명                                      |
                                                                                                                                                                                                                                        |---------------------|-----------------------------------------|
      | `by viewModels {}`  | Activity 생명주기와 연결해 ViewModel 생성/보존      |
      | `Factory`           | ViewModel 생성 시 `Repository` 등 외부 의존성 주입 |
      | ViewModelStoreScope | Activity가 회전해도 같은 인스턴스 유지               |


- Composable 내부
    - Compose UI는 ViewModel의 StateFlow를 구독 → 데이터 변경 시 UI 자동 갱신
        ```kotlin
        val viewModel: SavedRecipesViewModel = viewModel(
            factory = SavedRecipesViewModel.Factory
        )
        ```
      | 내용            | 설명                                                 |
                                                                                                                                                                                                              |---------------|----------------------------------------------------|
      | `viewModel()` | Compose에서 Activity/Fragment의 ViewModelStore와 자동 연결 |
      | `Factory`     | 외부 의존성 주입 (Repository 제공)                          |
      | UI 상태 구독      | `collectAsState()` 사용해 자동 UI 업데이트                  |

#### 안드로이드가 제공하는 ViewModel

- 생명주기를 고려하여 UI 관련 데이터를 관리하도록 설계된 클래스
- 화면 회전이나 환경 변경시에서 데이터를 유지할 수 있다
- ViewModel의 생명주기는 Activity의 일생과 함께 한다

| 직접 생성(new) ❌                  | Android 제공 함수 ⭕ |
|-------------------------------|-----------------|
| 화면 회전 시 사라짐                   | 상태를 유지          |
| Activity/Composable 마다 새 인스턴스 | 같은 인스턴스 재사용     |
| 메모리 누수 위험                     | 생명주기에 자동 정리     |
| 테스트 어려움                       | 의존성 주입이 쉬움      |

#### ViewModel 에 의존성 주입 방법 : ViewModelFactory(자주 바뀌니 문서 확인)

> ViewModel은 UI 비즈니스 로직을 담당하며 네트워크/DB/파일 등 데이터 출처(Repository)와 연결 필요

- Android에서 의존성 주입이 적절한 곳
  | 위치 | 적합한가? | 이유 |
  | --------------- | :---: | ------------------------------------- |
  | **Activity**    | ❌ | 생명주기가 계속 변함. 새 Activity 생성 시 다시 주입 필요 |
  | **Application** | ⭕ | 앱 전체에서 단 하나만 존재, 생명주기 안정적 |
    - Application에 Repository를 구성하고 → Factory로 ViewModel에 전달

- Application 재정의
    - Application 상속하고 AndroidManifest.xml 에 설정
- 싱글턴 객체 의존성 설정 (App 전체에서 Repository를 재사용하는 싱글톤 패턴)
    - 객체 생성 순서에 영향이 없도록 lazy 생성하는 것 추천
    - 순서가 중요하게 코드를 짜면 런타임에 터질 확률이 높다
    ```kotlin
    class AppApplication : Application() {
        val recipeRepository: RecipeRepository by lazy {
            RecipeRepositoryImpl(
                recipeDataSource = MockRecipeDataSourceImpl()
            )
        }
    }
    ```
- ViewModelFactory에서 Repository 주입
    ```kotlin
        companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository =
                    (this[APPLICATION_KEY] as AppApplication).recipeRepository
                SavedRecipesViewModel(repository)
            }
        }
    }
    ```
    - Application에서 Repository 가져와 ViewModel에 주입
    - 테스트 시 Mock Repository 전달도 가능 → 테스트 친화적

- Screen에 ViewModel 직접 주입
    ```kotlin
    val viewModel: SavedRecipesViewModel = viewModel(
        factory = SavedRecipesViewModel.Factory
    )
    
    val recipesState by viewModel.recipes.collectAsState()
    ```
    - UI는 ViewModel이 제공하는 상태만 구독하면 됨
    - 데이터 변경 → UI 자동 갱신


- State를 직접 상태로 가지는 ViewModel?
    - State는 컴포즈 전용이라 범용성(컴포즈 미도입 kotlin 프로젝트)을 위해서는 StateFlow (순수 코틀린 API) 가 낫다
      | 역할 | 책임 |
      | ---------------- | ---------------------------- |
      | AppApplication | Repository 생성 및 보관 (DI Root) |
      | ViewModelFactory | ViewModel + Repository 연결 |
      | ViewModel | 비즈니스 로직 & 상태 관리 |
      | Screen | ViewModel의 상태를 구독해 UI 렌더링 |

